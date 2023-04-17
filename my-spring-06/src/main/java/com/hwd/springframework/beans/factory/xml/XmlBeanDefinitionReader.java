package com.hwd.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.hwd.springframework.beans.BeansException;
import com.hwd.springframework.beans.factory.PropertyValue;
import com.hwd.springframework.beans.factory.config.BeanDefinition;
import com.hwd.springframework.beans.factory.config.BeanReference;
import com.hwd.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.hwd.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.hwd.springframework.core.io.Resource;
import com.hwd.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try (InputStream inputStream = resource.getInputStream()) {
            doLoadBeanDefinitions(inputStream);
        } catch (IOException | ClassNotFoundException e){
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            this.loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        Resource resource = getResourceLoader().getResource(location);
        this.loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        for (String location : locations) {
            Resource resource = getResourceLoader().getResource(location);
            this.loadBeanDefinitions(resource);
        }
    }

    /**
     * 解析XML配置文件
     * @param inputStream
     * @throws ClassNotFoundException
     */
    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException{
        Document doc = XmlUtil.readXML(inputStream);
        Element root = doc.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        if(childNodes.getLength() >0){
            for (int i = 0; i < childNodes.getLength(); i++) {
                // 判断元素
                if (!(childNodes.item(i) instanceof Element)) continue;
                // 判断对象
                if (!"bean".equals(childNodes.item(i).getNodeName())) continue;

                //获取配置信息：<bean id="userDao" class="cn.bugstack.springframework.test.bean.UserDao"/>
                Element bean = (Element) childNodes.item(i);
                String id = bean.getAttribute("id");
                String name = bean.getAttribute("name");
                String className = bean.getAttribute("class");

                //获取Class
                Class<?> clazz = Class.forName(className);
                //id的优先级大于name
                String beanName = StrUtil.isNotEmpty(id) ? name : id;
                //如果beanName为空，则取类名称第一个字母小写作为beanName
                if(StrUtil.isEmpty(beanName)){
                    beanName = StrUtil.lowerFirst(clazz.getSimpleName());
                }

                //定义Bean
                BeanDefinition beanDefinition = new BeanDefinition(clazz);
                //获取属性并填充:<property name="" value=""/>,<property name="" ref=""/>
                for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                    if (!(bean.getChildNodes().item(j) instanceof Element)) continue;
                    if (!"property".equals(bean.getChildNodes().item(j).getNodeName())) continue;
                    // 解析标签：property
                    Element property = (Element) bean.getChildNodes().item(j);
                    String attrName = property.getAttribute("name");
                    String attrValue = property.getAttribute("value");
                    String attrRef = property.getAttribute("ref");
                    // 获取属性值：引入对象、值对象
                    Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                    // 创建属性信息
                    PropertyValue propertyValue = new PropertyValue(attrName, value);
                    beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                }
                //判断Bean是否已经存在
                if (getBeanDefinitionRegistry().containsBeanDefinition(beanName)) {
                    throw new BeansException("Duplicate beanName[ " + beanName + "]  is not allowed");
                }
                //注册bean信息
                getBeanDefinitionRegistry().registerBeanDefinition(beanName,beanDefinition);

            }
        }
    }
}
