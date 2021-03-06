package com.spring.listener;

import com.Annotation.annotation.utils.ClassPathXmlApplicationContext1;
import com.spring.ApplicationContext;
import com.spring.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/20 13:25
 */
@WebListener(value = "ContextListener")
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        ApplicationContext applicationContext= (ApplicationContext) servletContext.getAttribute(ClassPathXmlApplicationContext1.CONTEXT_SERVLETCONTEXT_NAME);
        if (applicationContext!=null)
            throw new RuntimeException("不能创建多个spring");
        //获取配置文件
        String initParameter = servletContext.getInitParameter(ClassPathXmlApplicationContext1.CONTEXT_LOCATION);
        //创建spring放入servletContext域
        applicationContext=  new ClassPathXmlApplicationContext1(initParameter);
        servletContext.setAttribute(ClassPathXmlApplicationContext1.CONTEXT_SERVLETCONTEXT_NAME,applicationContext);

    }
}
