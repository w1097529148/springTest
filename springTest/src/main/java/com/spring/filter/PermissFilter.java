package com.spring.filter;

import com.Annotation.annotation.UserThreadLocal;
import com.spring.ApplicationContext;
import com.spring.bean.Privilege;
import com.spring.bean.User;
import com.spring.serviceImpl.SecurityService;
import com.spring.utils.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/23 16:46
 */
//@WebFilter(filterName = "permissonFilter",urlPatterns = "/*")
public class PermissFilter implements Filter {
    private SecurityService userService;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ApplicationContext applicationContext = WebApplicationContextUtils.getApplicationContext(filterConfig.getServletContext());
       userService = (SecurityService) applicationContext.getBean("securityService");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest servletRequest1 = (HttpServletRequest) servletRequest;
        HttpServletResponse servletResponse1 = (HttpServletResponse) servletResponse;
        String requestURI = servletRequest1.getRequestURI();
        String contextPath = servletRequest1.getContextPath();
        System.out.println("contextPath = " + contextPath);
        StringBuffer requestURI1 = servletRequest1.getRequestURL();
        System.out.println("requestURI1 = " + requestURI1);
        System.out.println("requestURI = " + requestURI);
        //对登录页面，登录处理servlet，权限分页servlet放行
        if (requestURI.equalsIgnoreCase("/index.jsp")|requestURI.equalsIgnoreCase("/")|requestURI.contains("/LoginServlet")|requestURI.contains("/SecurityServlet")
       )
        {
            filterChain.doFilter(servletRequest1,servletResponse1);
            return;
        }
        HttpSession session = servletRequest1.getSession();
        if (session==null){
            servletResponse1.sendRedirect("index.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        UserThreadLocal.setUser(user);
        System.out.println("user = " + user);
        if (user==null){
            servletResponse1.sendRedirect("index.jsp");
            return;
        }
        List<Privilege> privileges = userService.querySecurityByRoleId(user.getId());
        boolean isOk=false;
        //servletRequest1.getQueryString() 获取参数字符串
        String s = requestURI + "?" + servletRequest1.getQueryString();
        if (s.contains("index.jsp"))
            isOk=true;
        if (!isOk){
            for (Privilege privilege : privileges) {
                if (s.contains("head.jsp")){
                    isOk=true;
                    break;
                }
            }
        }
        if (!isOk){
            servletResponse1.sendRedirect("index.jsp");
        }
        filterChain.doFilter(servletRequest1,servletResponse1);
        UserThreadLocal.remove();

    }

    @Override
    public void destroy() {

    }
}
