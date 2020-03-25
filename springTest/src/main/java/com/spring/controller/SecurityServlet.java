package com.spring.controller;

import com.Annotation.annotation.di.Autowired;
import com.spring.ApplicationContext;
import com.spring.bean.Page;
import com.spring.bean.PageBean;
import com.spring.bean.Privilege;
import com.spring.serviceImpl.SecurityService;
import com.spring.utils.WebApplicationContextUtils;
import org.apache.commons.collections.CollectionUtils;

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
@WebServlet(name="SecurityServlet",urlPatterns = "/SecurityServlet")
public class SecurityServlet extends HttpServlet {

        private static final long serialVersionUID = -4443656491740044602L;
        @Autowired
        private SecurityService security;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ApplicationContext applicationContext = WebApplicationContextUtils.getApplicationContext(getServletContext());
        security = (SecurityService) applicationContext.getBean("securityService");
    }

    @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            doPost(req, resp);
        }
//        分页
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            PageBean pageBean=new PageBean();
            pageBean.setRequest(req);
            pageBean.setTotal(security.querySecurityTotal());
            System.out.println("pageBean = " + pageBean);
            List<Privilege> privileges = security.querySecurityPage(pageBean);
            if (CollectionUtils.isNotEmpty(privileges))
                req.setAttribute("list",privileges);
            req.setAttribute("pagebean", pageBean);
                req.getRequestDispatcher("/security/page.jsp").forward(req, resp);

        }

    }

