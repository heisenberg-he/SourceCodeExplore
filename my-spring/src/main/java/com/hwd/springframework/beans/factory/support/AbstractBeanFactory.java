package com.hwd.springframework.beans.factory.support;

import com.hwd.springframework.BeanFactory;
import com.hwd.springframework.beans.BeansException;
import com.hwd.springframework.beans.factory.config.BeanDefinition;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
   @Override
    public Object getBean(String name) throws BeansException {
      return doGetBean(name,null);
   }

   @Override
   public Object getBean(String name, Object... args) throws BeansException {
      return doGetBean(name,args);
   }

   protected Object doGetBean(String name, Object... args){
      Object bean = getSingleton(name);
      if(bean != null) return bean;
      BeanDefinition beanDefinition = getBeanDefinition(name);
      return createBean(name,beanDefinition,args);
   }
   protected abstract Object createBean(String beanName, BeanDefinition beanDefinition,Object []args) throws BeansException;

   protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
