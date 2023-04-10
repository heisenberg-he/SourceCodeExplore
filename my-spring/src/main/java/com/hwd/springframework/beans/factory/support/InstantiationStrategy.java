package com.hwd.springframework.beans.factory.support;

import com.hwd.springframework.beans.BeansException;
import com.hwd.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * Bean实例化策略接口
 */
public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition,String beanName, Constructor ctor,Object [] args)throws BeansException;
}