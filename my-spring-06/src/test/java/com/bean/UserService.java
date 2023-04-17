package com.bean;

public class UserService {
    private String name;

    private UserDao userDao;

    public UserService(){}

    public UserService(String name, UserDao userDao) {
        this.name = name;
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void getUserInfo(){
        System.out.println("用户信息=====>" + name +"--"+ userDao);
    }

    @Override
    public String toString() {
        return "UserService{" +
                "name='" + name + '\'' +
                ", userDao=" + userDao +
                '}';
    }
}
