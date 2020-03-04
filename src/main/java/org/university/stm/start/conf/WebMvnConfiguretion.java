package org.university.stm.start.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.university.stm.start.web.handler.RestInterceptorHandler;
import org.university.stm.start.web.handler.ServerInterceptorHandler;

/**
 * <pre>
 * </pre>
 *
 * @author : ting.chen
 * @date : 2020-02-21
 */
@Configuration
public class WebMvnConfiguretion implements WebMvcConfigurer {

    @Autowired
    private ServerInterceptorHandler serverInterceptorHandler;

    @Autowired
    private RestInterceptorHandler restInterceptorHandler;

    /**
     * 添加静态资源访问
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/render/**").addResourceLocations("classpath:/render/");
    }

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(serverInterceptorHandler)
                .addPathPatterns("/")
                .addPathPatterns("/server/**");

        registry.addInterceptor(restInterceptorHandler)
                .addPathPatterns("/stm/api/v1/**")
                .excludePathPatterns("/stm/api/v1/signin");
    }
}
