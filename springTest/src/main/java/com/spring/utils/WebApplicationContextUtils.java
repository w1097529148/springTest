package com.spring.utils;

import com.spring.ApplicationContext;
import com.spring.ClassPathXmlApplicationContext;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/20 13:18
 */
public class WebApplicationContextUtils {
    public static ApplicationContext getApplicationContext(ServletContext servletContext){
        return (ApplicationContext) servletContext.getAttribute(ClassPathXmlApplicationContext.CONTEXT_SERVLETCONTEXT_NAME);
    }
}
