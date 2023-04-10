package com.hwd.springframework.beans.factory.config;

public class BeanDefinition {
    private Class beanClass;

    public BeanDefinition() {
    }
    public BeanDefinition(Class bean) {
        this.beanClass = bean;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class bean) {
        this.beanClass = bean;
    }
}

