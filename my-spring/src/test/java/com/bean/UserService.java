package com.bean;

public class UserService {
    private String name;

    public UserService(){}
    public UserService(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void getUserInfo(){
        System.out.println("用户信息=====>" + name);
    }
}
