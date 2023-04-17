package com.hwd.springframework.beans;

/***
 * @author hwd
 * 自定义异常
 */
public class BeansException extends RuntimeException{
    public BeansException(String message){
        super(message);
    }

    public BeansException(String message, Throwable cause){
        super(message, cause);
    }
}
