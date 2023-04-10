package com.hwd.springframework;

import com.hwd.springframework.beans.BeansException;

public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    Object getBean(String name,Object...args) throws BeansException;
}
