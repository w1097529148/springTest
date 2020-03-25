package com.Annotation.annotation.aop;

import com.Annotation.annotation.aop.bean.Person;
import com.Annotation.annotation.aop.impl.PersonImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/25 10:10
 */
//implements InvocationHandler (改版2 使用内部类)
public class PersonProxy  {
    private Person person=new PersonImpl();
//    public Person createProxy(){
//        return (Person) Proxy.newProxyInstance(person.getClass().getClassLoader(),person.getClass().getInterfaces(),new MyInvoke(person));
//    }


//    // 改版1
//    public Person createProxy(){
//        return (Person) Proxy.newProxyInstance(person.getClass().getClassLoader(),person.getClass().getInterfaces(),this);
//    }

    public Person createProxy(){
        return (Person) Proxy.newProxyInstance(person.getClass().getClassLoader(), person.getClass().getInterfaces(), new InvocationHandler() {
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
        });
    }


}
