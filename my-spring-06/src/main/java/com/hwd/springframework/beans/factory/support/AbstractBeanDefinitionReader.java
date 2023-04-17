package com.hwd.springframework.beans.factory.support;

import com.hwd.springframework.core.io.DefaultResourceLoader;
import com.hwd.springframework.core.io.ResourceLoader;

public abstract class AbstractBeanDefinitionReader  implements BeanDefinitionReader{
    private final  BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry){
        this(registry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader){
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getBeanDefinitionRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
