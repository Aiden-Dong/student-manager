package org.university.stm.start;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <pre>
 *     服务运行启动类
 * </pre>
 *
 * @author : ting.chen
 * @date : 2020-02-21
 */
@SpringBootApplication
@MapperScan("org.university.stm.start.dao.mybatis")
public class StudentManagerApplication {
    public static void main(String[] args){
        SpringApplication.run(StudentManagerApplication.class);
    }
}
