package com.Annotation.annotation.aop;

import com.Annotation.annotation.aop.bean.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/25 10:13
 */
public class MyInvoke implements InvocationHandler {
    private Person person;
    public MyInvoke(Person person) {
        this.person=person;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String name = method.getName();
        if ("sing".equals(name)){
            String invoke = (String) method.invoke(person, args);
            return invoke+"增强方法";
        }else if ("dance".equals(name)){
            String dance= (String) method.invoke(person,args);
            return dance+"增强dance方法";
        }
        return null;
    }
}
