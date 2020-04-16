package com.bee.sample.ecs;

import com.bee.sample.ecs.preHandle.CustomHandlerInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootConfiguration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        registry.addInterceptor(new CustomHandlerInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}
