package com.upala.wong.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/********************************
 *  @program image
 *  @author upala
 *  @version 0.0.1
 *  @since 2019-11-01 14:41
 *  @description 项目配置信息
 ********************************/

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 页面跳转
     * @param registry 参数
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/upala/core.html").setViewName("core");
    }

    /**
     * 加载静态资源
     * @param registry 参数
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

}
