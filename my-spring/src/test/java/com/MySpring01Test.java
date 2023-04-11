package com;


import com.bean.UserDao;
import com.bean.UserService;
import com.hwd.springframework.beans.factory.PropertyValue;
import com.hwd.springframework.beans.factory.PropertyValues;
import com.hwd.springframework.beans.factory.config.BeanDefinition;
import com.hwd.springframework.beans.factory.config.BeanReference;
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



    @Test
    public void test4(){
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        //注册userDao
        BeanDefinition userDaoDefinition = new BeanDefinition();
        userDaoDefinition.setBeanClass(UserDao.class);
        PropertyValues propertyValues1 = new PropertyValues();
        propertyValues1.addPropertyValue(new PropertyValue("username","root"));
        propertyValues1.addPropertyValue(new PropertyValue("password","root123"));
        userDaoDefinition.setPropertyValues(propertyValues1);
        defaultListableBeanFactory.registerBeanDefinition("userDao", userDaoDefinition);

        UserDao userDao = (UserDao) defaultListableBeanFactory.getBean("userDao");
        System.out.println(userDao);

        BeanDefinition userServiceDefinition = new BeanDefinition();
        userServiceDefinition.setBeanClass(UserService.class);

        PropertyValues propertyValues = new PropertyValues();
        //userService里的name
        propertyValues.addPropertyValue(new PropertyValue("name","heisenberg"));
        //userService里的userDao
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));
        userServiceDefinition.setPropertyValues(propertyValues);
        //注册userService
        defaultListableBeanFactory.registerBeanDefinition("userService",userServiceDefinition);
        UserService userService = (UserService) defaultListableBeanFactory.getBean("userService");
        System.out.println(userService);
    }
}
