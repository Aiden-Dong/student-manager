package org.university.stm.start.web.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.university.stm.start.bean.AuthManager;

import javax.security.auth.message.AuthException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 *     rest intercept
 * </pre>
 *
 * @author : ting.chen
 * @date : 2020-03-04
 */
@Controller
public class RestInterceptorHandler extends HandlerInterceptorAdapter {
    private static final String AUTH_HEADER = "Authorization";
    private static final String BASE_AUTH = "Basic ";

    @Autowired
    private AuthManager authManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(AUTH_HEADER);

        if (token != null && token.startsWith(BASE_AUTH)) {
            authManager.auth(token.substring(token.indexOf(BASE_AUTH)));
            return true;
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(AUTH_HEADER)) {
                    authManager.auth(cookie.getValue());
                    return true;
                }
            }
        }

        throw new AuthException("Invalid token message");
    }
}
