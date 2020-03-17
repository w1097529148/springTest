package com.spring;

/**
 * @Description 测试类
 * @Author Mr.Li
 * @Date 2020/3/17 15:22
 */
public class Test {
    @org.junit.Test
    public  void Test(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Object bean = applicationContext.getBean("brand");
        Object car = applicationContext.getBean("car");
        System.out.println("car = " + car);
        System.out.println("bean = " + bean);
    }
}
