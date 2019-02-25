package com.footprint.boot.config;

import com.footprint.boot.interceptor.MyHandlerExceptionResolver;
import com.footprint.boot.interceptor.MyHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.DigestUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.ContentVersionStrategy;
import org.springframework.web.servlet.resource.VersionResourceResolver;

import java.io.IOException;
import java.util.List;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyHandlerInterceptor());
    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(0, new MyHandlerExceptionResolver());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 通过文件MD5作为版本号，访问
         * http://localhost:8081/spring-boot/js/webappsrc-5363c1f9ec8274c9dd20a8156c9f2ce7.js
         * http://localhost:8081/spring-boot/js/javasrc-b22f7fce6395ee49d36a8815cc6708ab.js
         */
        VersionResourceResolver versionResourceResolver = new VersionResourceResolver()
                .addVersionStrategy(new ContentVersionStrategy(), "/**");
        registry.addResourceHandler("/js/*.js")
                .addResourceLocations("/static/","classpath:/static/")
                .setCachePeriod(60 * 60 * 24 * 365)
                .resourceChain(true)
                .addResolver(versionResourceResolver);

        /**
         * 通过指定的版本号访问静态文件
         * http://localhost:8081/spring-boot/2.0.0/v/classpath.js
         * http://localhost:8081/spring-boot/2.0.0/v/webapp.js
         */
        VersionResourceResolver fixVersionResourceResolver = new VersionResourceResolver()
                .addFixedVersionStrategy("2.0.0", "/**");
        registry.addResourceHandler("/2.0.0/v/*.js")
                .addResourceLocations("/static/","classpath:/static/")
                .setCachePeriod(60 * 60 * 24 * 365) /* one year */
                .resourceChain(true)
                .addResolver(fixVersionResourceResolver);
    }

    /**
     * 支持跨域访问
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**");
    }

    public static void main(String[] args){
        Resource resource = new ClassPathResource("/static/javasrc.js");
        try {
            byte[] content = FileCopyUtils.copyToByteArray(resource.getInputStream());
            String s =  DigestUtils.md5DigestAsHex(content);
            System.out.println(s);
        }
        catch (IOException ex) {
            throw new IllegalStateException("Failed to calculate hash for " + resource, ex);
        }
    }
}
