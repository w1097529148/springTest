package com.Annotation.annotation.aop.impl;

import com.Annotation.annotation.aop.bean.Person;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/25 9:50
 */
public class PersonImpl implements Person {
    @Override
    public String sing(String name) {
        System.out.println("name = " + name);
        return "sing";
    }

    @Override
    public String dance(String name) {
        System.out.println("name = " + name);
        return "dance";
    }
}
