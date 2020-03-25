package com.spring;

import com.Annotation.annotation.utils.ClassPathXmlApplicationContext;
import com.spring.serviceImpl.RoleServiceImpl;
import org.junit.Test;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/18 10:16
 */
public class testServiceAnno {
    @Test
    public void test(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:annotationContext.xml");
        Object testBean = classPathXmlApplicationContext.getBean("testBean");
        System.out.println("testBean = " + testBean);
    }
}
