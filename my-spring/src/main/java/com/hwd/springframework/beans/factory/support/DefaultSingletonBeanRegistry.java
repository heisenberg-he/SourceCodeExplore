package com.hwd.springframework.beans.factory.support;

import com.hwd.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    private Map<String,Object> singletonObjects = new HashMap<>(256);
    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonObject){
        singletonObjects.put(beanName, singletonObject);
    }

}
