package com.footprint.boot;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

/**
 * SpringBootServletInitializer
 *      需要servlet3.0+，否则无用
 */
@SpringBootApplication
@ImportResource({"classpath:spring-application.xml"})
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {
        //禁用热启动
        ///System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(Application.class, args);
    }

    /*@Bean
    public ExitCodeGenerator exitCodeGenerator() {
        return () -> 42;
    }

    public static void main(String[] args) {
        System.exit(SpringApplication
                .exit(SpringApplication.run(Application.class, args)));
    }*/
}
