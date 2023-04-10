package com;


import com.bean.UserService;
import com.hwd.springframework.beans.factory.config.BeanDefinition;
import com.hwd.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

public class MySpring01Test {
    @Test
    public void test1() {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClass(UserService.class);
        defaultListableBeanFactory.registerBeanDefinition("userService", beanDefinition);
        UserService userService =(UserService) defaultListableBeanFactory.getBean("userService");
        userService.getUserInfo();
    }



    @Test
    public void test2(){
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClass(UserService.class);
        defaultListableBeanFactory.registerBeanDefinition("userService",beanDefinition);
        UserService userService =  (UserService)defaultListableBeanFactory.getBean("userService","yubo");
        userService.getUserInfo();
    }
}
