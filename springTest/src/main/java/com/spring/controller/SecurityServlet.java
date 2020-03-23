package com.spring.controller;

import com.spring.ApplicationContext;
import com.spring.bean.Page;
import com.spring.bean.Privilege;
import com.spring.serviceImpl.SecurityService;
import com.spring.utils.WebApplicationContextUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/20 13:36
 */
@WebServlet("/SecurityServlet")
public class SecurityServlet extends HttpServlet {
    private SecurityService securityService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        ApplicationContext applicationContext= WebApplicationContextUtils.getApplicationContext(getServletContext());
        securityService= (SecurityService) applicationContext.getBean("securityService");
    }
    private List<Privilege> PageQuery(HttpServletRequest request, HttpServletResponse response){
        String total = request.getParameter("total").trim();//总条数
        String currentPage = request.getParameter("currentPage").trim();//当前页数
        String pageSize = request.getParameter("pageSize").trim();//页面显示条数
        String pages = request.getParameter("pages").trim();//总页数
        if (StringUtils.isAllBlank(total,currentPage,pages,pageSize))
            return null;
        return securityService.querySecurityPage(new Page(1,3));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        List<Privilege> privileges = PageQuery(req, resp);
        System.out.println("privileges = " + privileges);
        req.setAttribute("privileges",privileges);
        req.getRequestDispatcher("/security/listPrivilege.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
