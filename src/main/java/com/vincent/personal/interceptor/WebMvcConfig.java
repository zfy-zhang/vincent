package com.vincent.personal.interceptor;

import com.vincent.personal.util.MyUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * Created with IDEA
 * author:vincent
 * Date:2018/11/7
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private BaseInterceptor baseInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(baseInterceptor);
//                .addPathPatterns("**")
//                .excludePathPatterns("/");

        registry.addInterceptor(baseInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/login","/static/**");
//                .excludePathPatterns("/admin/login","login","/static/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:"+ MyUtils.getUploadFilePath()+"upload/");

        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**")
                .addResourceLocations("classpath:/templates/");

        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
