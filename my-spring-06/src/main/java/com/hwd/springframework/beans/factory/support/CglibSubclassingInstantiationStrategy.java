package com.hwd.springframework.beans.factory.support;

import com.hwd.springframework.beans.BeansException;
import com.hwd.springframework.beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy{
    /**
     * cglib 实例化
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        /**
         * Enhancer是cglib中使用频率很高的一个类，它是一个字节码增强器，
         * 可以用来为无接口的类创建代理。它的功能与java自带的Proxy类挺相似的。
         *它会根据某个给定的类创建子类，并且所有非final的方法都带有回调钩子。
         */
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        //NoOp回调，就是啥都不干的意思
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        if(ctor == null) return enhancer.create();
        return enhancer.create(ctor.getParameterTypes(),args);
    }
}
