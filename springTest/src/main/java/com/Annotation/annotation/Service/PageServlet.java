package com.Annotation.annotation.Service;

import com.Annotation.annotation.aop.Aspect;
import com.Annotation.annotation.di.Autowired;
import com.spring.ApplicationContext;
import com.spring.bean.PageBean;
import com.spring.bean.Privilege;
import com.spring.utils.MybatisUtils;
import com.spring.utils.WebApplicationContextUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
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
@WebServlet(name = "PageServlet1",urlPatterns="/PageServlet1")
@Aspect
public class PageServlet extends HttpServlet {
    @Autowired
    private SecurityService securityService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        ApplicationContext applicationContext = WebApplicationContextUtils.getApplicationContext(getServletContext());
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        System.out.println("method = " + method);
        if (StringUtils.isNotBlank(method)) {
            //查询总数
            if (method.equalsIgnoreCase("querySecurityTotal")) {
                Integer integer = securityService.querySecurityTotal();
                MybatisUtils.commitTransaction();
                if (integer>0)
                req.setAttribute("message", integer);
                req.getRequestDispatcher("security/listPrivilege.jsp").forward(req, resp);
            } else if (method.equalsIgnoreCase("findSecurityById")) {
//                根据id查询
                Privilege securityById = securityService.findSecurityById(2);
                MybatisUtils.commitTransaction();
                if (ObjectUtils.isNotEmpty(securityById))
                req.setAttribute("message", securityById);
                req.getRequestDispatcher("security/listPrivilege.jsp").forward(req, resp);
            } else if (method.equalsIgnoreCase("insertSecurity")) {
                //新增
                Integer integer = securityService.insertSecurity(new Privilege(2, "5", "7", "9"));
                MybatisUtils.commitTransaction();
                if (integer>0)
                req.setAttribute("message", integer);
                req.getRequestDispatcher("security/listPrivilege.jsp").forward(req, resp);
            } else if (method.equalsIgnoreCase("updateSecurity")) {
                //修改
                Integer integer = securityService.updateSecurity(new Privilege(2, "6", "7", "9"));
                MybatisUtils.commitTransaction();
                if (integer>0)
                req.setAttribute("message", integer);
                req.getRequestDispatcher("security/listPrivilege.jsp").forward(req, resp);
            } else if (method.equalsIgnoreCase("deleteSecurity")) {
                //删除
                Integer integer = securityService.deleteSecurityById(1);
                MybatisUtils.commitTransaction();
                if (integer>0)
                req.setAttribute("message", integer);
                req.getRequestDispatcher("security/listPrivilege.jsp").forward(req, resp);
            } else if (method.equalsIgnoreCase("querySecurityPage")) {
                //分页数据
                List<Privilege> privileges = securityService.querySecurityPage(new PageBean());
                if (CollectionUtils.isNotEmpty(privileges))
//                List<Privilege> privileges = securityService.querySecurityPage(new Page(1,3));
                MybatisUtils.commitTransaction();
                req.setAttribute("message",privileges );
                req.getRequestDispatcher("security/listPrivilege.jsp").forward(req, resp);
            }else if (method.equalsIgnoreCase("querySecurityAll")) {
                //查询全部
                List<Privilege> privileges = securityService.querySecurityAll(new Privilege(null,null,null,null));
//                List<Privilege> privileges = securityService.querySecurityPage(new Page(1,3));
                MybatisUtils.commitTransaction();
                if (CollectionUtils.isNotEmpty(privileges))
                req.setAttribute("message",privileges );
                req.getRequestDispatcher("security/listPrivilege.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req,resp);
    }
}
