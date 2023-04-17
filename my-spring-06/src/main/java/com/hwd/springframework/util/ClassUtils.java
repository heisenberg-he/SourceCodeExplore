package com.hwd.springframework.util;


public class ClassUtils {
    /**
     * 获取默认的ClassLoader，如果当前线程有上下文ClassLoader，则使用上下文ClassLoader，否则使用当前类的ClassLoader。
     * 具体实现如下：
     * 首先尝试获取当前线程的上下文ClassLoader，通过Thread.currentThread().getContextClassLoader()方法实现。
     * 如果获取不到上下文ClassLoader，则使用当前类的ClassLoader，通过ClassUtils.class.getClassLoader()方法实现。
     * 如果当前线程的上下文ClassLoader和当前类的ClassLoader都获取不到，则返回null。
     */
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl  = null;
        try {
             cl =  Thread.currentThread().getContextClassLoader();
        }catch (Throwable e) {

        }

        if (cl == null) {
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }
}
