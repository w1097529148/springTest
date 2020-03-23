package com.spring.controller;

import com.spring.ApplicationContext;
import com.spring.ClassPathXmlApplicationContext;
import com.spring.bean.Page;
import com.spring.bean.Privilege;
import com.spring.serviceImpl.SecurityService;
import com.spring.utils.MybatisUtils;
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
 * @Date 2020/3/20 12:55
 */
@WebServlet("/PageServlet")
public class PageServlet extends HttpServlet {
    private SecurityService securityService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        ApplicationContext applicationContext = WebApplicationContextUtils.getApplicationContext(getServletContext());
        securityService= (SecurityService) applicationContext.getBean("securityService");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        System.out.println("method = " + method);
        if (StringUtils.isNotBlank(method))
            if (method.equalsIgnoreCase("querySecurityTotal")) {
                Integer integer = securityService.querySecurityTotal();
                MybatisUtils.commitTransaction();
                req.setAttribute("message",integer );
                req.getRequestDispatcher("security/index.jsp").forward(req, resp);
            } else if (method.equalsIgnoreCase("findSecurityById")) {
                List<Privilege> privileges = securityService.querySecurityAll(null);
                MybatisUtils.commitTransaction();
                req.setAttribute("message", privileges);
                req.getRequestDispatcher("security/index.jsp").forward(req, resp);
            } else if (method.equalsIgnoreCase("insertSecurity")) {
                Integer integer = securityService.insertSecurity(new Privilege(3, "5", "7", "9"));
                MybatisUtils.commitTransaction();
                req.setAttribute("message", integer);
                req.getRequestDispatcher("security/index.jsp").forward(req, resp);
            } else if (method.equalsIgnoreCase("updateSecurity")) {
                Integer integer = securityService.updateSecurity(new Privilege(3, "6", "7", "9"));
                MybatisUtils.commitTransaction();
                req.setAttribute("message", integer);
                req.getRequestDispatcher("security/index.jsp").forward(req, resp);
            } else if (method.equalsIgnoreCase("deleteSecurity")) {
                Integer integer = securityService.deleteSecurityById(1);
                MybatisUtils.commitTransaction();
                req.setAttribute("message",integer );
                req.getRequestDispatcher("security/index.jsp").forward(req, resp);
            } else if (method.equalsIgnoreCase("querySecurityPage")) {
                List<Privilege> privileges = securityService.querySecurityPage(new Page(1,3));
                MybatisUtils.commitTransaction();
                req.setAttribute("message",privileges );
                req.getRequestDispatcher("security/index.jsp").forward(req, resp);
            }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req,resp);
    }
}
