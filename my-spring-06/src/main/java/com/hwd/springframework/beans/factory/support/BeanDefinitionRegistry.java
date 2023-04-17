package com.hwd.springframework.beans.factory.support;

import com.hwd.springframework.beans.BeansException;
import com.hwd.springframework.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {
    /**
     * 注册beanDefinition
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 根据Bean名称查询是否已经存在
     * @param beanName
     * @return
     * @throws BeansException
     */
    Boolean containsBeanDefinition(String beanName) throws BeansException;

}
