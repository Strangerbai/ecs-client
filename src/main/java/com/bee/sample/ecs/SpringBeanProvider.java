package com.bee.sample.ecs;

import org.example.rpc.server.engine.BeanProvider;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * Created by niechang on 2017/3/31.
 */
public class SpringBeanProvider implements BeanProvider, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public <T> T getBean(Class<T> beanClass) {
        return applicationContext.getBean(beanClass);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> beanClass) {
        return applicationContext.getBean(beanName, beanClass);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return this.applicationContext.getBeansOfType(type);
    }
}
