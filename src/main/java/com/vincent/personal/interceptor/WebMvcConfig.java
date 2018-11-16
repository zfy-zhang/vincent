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
        registry.addInterceptor(baseInterceptor).addPathPatterns("/admin/**").
                addPathPatterns("/")
//                .addPathPatterns("admin/**")
                .addPathPatterns("/page/**")
                .addPathPatterns("/article/**")
                .addPathPatterns("/archives")
                .addPathPatterns("/links")
                .addPathPatterns("/about")
                .addPathPatterns("/search/**")
                .addPathPatterns("/category")
//                .addPathPatterns("/admin/setting/**")
                .excludePathPatterns("/admin/login","/admin/logout","/admin/js/**","/admin/css/**","/admin/images/**","/admin/plugins/**","/static/**","/vincent/assets/**","/user/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:"+ MyUtils.getUploadFilePath()+"upload/");

        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**")
                .addResourceLocations("classpath:/templates/");

    }
}
