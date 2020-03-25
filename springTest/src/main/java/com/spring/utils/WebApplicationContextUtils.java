package com.spring.utils;

import com.Annotation.annotation.utils.ClassPathXmlApplicationContext1;
import com.spring.ApplicationContext;

import javax.servlet.ServletContext;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/20 13:18
 */
public class WebApplicationContextUtils {
    public static ApplicationContext getApplicationContext(ServletContext servletContext){
        return (ApplicationContext) servletContext.getAttribute(ClassPathXmlApplicationContext1.CONTEXT_SERVLETCONTEXT_NAME);
    }
}
