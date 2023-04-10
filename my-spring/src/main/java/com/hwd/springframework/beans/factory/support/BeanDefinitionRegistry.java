package com.hwd.springframework.beans.factory.support;

import com.hwd.springframework.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {
    /**
     * 注册beanDefinition
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
