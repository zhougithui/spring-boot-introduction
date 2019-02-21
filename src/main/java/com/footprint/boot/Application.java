package com.footprint.boot;

import com.footprint.boot.web.IndexController;
import com.footprint.boot.yaml.AcmeProperties;
import com.footprint.boot.yaml.YamlBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
    @Value("${my.secret}")
    private String name;

    @Value("${server.address}")
    private String serverAddress;

    @Autowired
    private AcmeProperties acmeProperties;

    @Bean
    public IndexController indexController(YamlBean yamlBean){
        AcmeProperties acme = anotherComponent();
        return new IndexController();
    }

    /**
     * acme开头的属性都会被注入新的对象中
     * @return
     */
    @ConfigurationProperties(prefix = "acme")
    @Bean
    public AcmeProperties anotherComponent() {
        return new AcmeProperties();
    }

}
