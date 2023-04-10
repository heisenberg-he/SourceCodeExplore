package com.hwd.springframework.beans.factory.support;

import com.hwd.springframework.beans.BeansException;
import com.hwd.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();
    @Override
    protected Object createBean(String beanName,BeanDefinition beanDefinition,Object [] args) throws BeansException {
        Object bean  =null;
        try {
            bean =createBeanInstance(beanName,beanDefinition,args);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed",e);
        }
        addSingleton(beanName,bean);
        return bean;
    }

    protected Object createBeanInstance(String beanName,BeanDefinition beanDefinition,Object [] args){
        //有参数先找对应的构造函数
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        //根据参数个数进行对比，找到对应的构造函数(实际 Spring 源码中还需要比对入参类型)
        for (Constructor<?> constructor : declaredConstructors) {
            if(null != args && constructor.getParameterTypes().length == args.length){
                constructorToUse = constructor;
                break;
            }
        }

        return getInstantiationStrategy().instantiate(beanDefinition,beanName,constructorToUse,args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
