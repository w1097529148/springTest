package com.Annotation.annotation.test;

import com.Annotation.annotation.Service.SecurityService;
import com.Annotation.annotation.Service.testService;
import com.Annotation.annotation.aop.PersonProxy;
import com.Annotation.annotation.aop.bean.Person;
import com.Annotation.annotation.utils.ClassPathXmlApplicationContext1;
import com.spring.bean.Privilege;
import org.junit.Test;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/25 8:27
 */
public class testAnnotation {
    @Test
    public void test(){
        ClassPathXmlApplicationContext1 classPathXmlApplicationContext1 = new ClassPathXmlApplicationContext1("classpath:annotationContext.xml");
        testService testBean = (testService) classPathXmlApplicationContext1.getBean("testService");
       testBean.sayhello();
    }
    @Test
    public void testProxy(){
        PersonProxy personProxy = new PersonProxy();
        Person proxy = personProxy.createProxy();
        String 肚皮舞 = proxy.dance("肚皮舞");
        System.out.println("肚皮舞 = " + 肚皮舞);
    }

    @Test
    public void test1(){
        ClassPathXmlApplicationContext1 classPathXmlApplicationContext1 = new ClassPathXmlApplicationContext1("classpath:annotationContext.xml");
        SecurityService pageServlet = (SecurityService) classPathXmlApplicationContext1.getBean("SecurityService");
        Privilege securityById = pageServlet.findSecurityById(1);
        System.out.println("securityById = " + securityById);
    }

    @Test
    public void test2(){
        ClassPathXmlApplicationContext1 classPathXmlApplicationContext1 = new ClassPathXmlApplicationContext1("classpath:annotationContext.xml");
        SecurityService pageServlet = (SecurityService) classPathXmlApplicationContext1.getBean("SecurityService");
        Privilege securityById = pageServlet.findSecurityById(1);
        System.out.println("securityById = " + securityById);
    }
}
