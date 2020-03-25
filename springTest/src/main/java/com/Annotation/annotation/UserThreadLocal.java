package com.Annotation.annotation;

import com.spring.bean.User;

/**
 * @Description
 * @Author Mr.Li
 * @Date 2020/3/25 13:32
 */
public class UserThreadLocal {
    private static ThreadLocal<User> threadLocal=new ThreadLocal<>();
    // 将用户加入到当前线程
    public static void setUser(User user){
        threadLocal.set(user);
    }
    // 获取当前用户
    public  static User getCurrentUser(){
        return threadLocal.get();
    }
    // 删除当前线程用户
    public static void remove(){
        threadLocal.remove();
    }
}
