package com.spring.controller;

import com.spring.ApplicationContext;
import com.spring.bean.Privilege;
import com.spring.bean.User;
import com.spring.serviceImpl.SecurityService;
import com.spring.serviceImpl.UserServiceImpl;
import com.spring.utils.MybatisUtils;
import com.spring.utils.WebApplicationContextUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginServlet",urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private UserServiceImpl userService;
//    权限判定
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id").trim();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        System.out.println("password = " + password);
        if (!StringUtils.isAllBlank(username,password,id)){
            ApplicationContext applicationContext = WebApplicationContextUtils.getApplicationContext(getServletContext());
           userService= (UserServiceImpl) applicationContext.getBean("userServiceImpl");
           //根据id查询角色是否存在
            User userById = userService.findUserById(Integer.valueOf(id));
            System.out.println("userById = " + userById);
            MybatisUtils.commitTransaction();
            if (userById!=null){
                //登录信息验证
                User login = userService.login(Integer.valueOf(id),username, password);
                System.out.println("login = " + login);
                if (login!=null){
                    request.getSession().setAttribute("user",login);
                    System.out.println("request = " + request.getSession().getAttribute("user"));
                   SecurityService securityService= (SecurityService) applicationContext.getBean("securityService");
                   //查询角色拥有的权限
                    List<Privilege> privileges = securityService.querySecurityByRoleId(Integer.valueOf(id));
                    if (CollectionUtils.isNotEmpty(privileges))
//                        判定权限跳转相应页面
                    for (Privilege privilege : privileges) {
                        if (privilege.getName().equalsIgnoreCase("admin")){
                            request.getSession().setAttribute("security",privilege);
                            response.sendRedirect("security/page.jsp");
                            return;
                        }else if(privilege.getName().equalsIgnoreCase("putong")){
                            response.sendRedirect("security/putong.jsp");
                            return;
                        }
                    }
                }

            }
        }
        request.setAttribute("message",null);
        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
