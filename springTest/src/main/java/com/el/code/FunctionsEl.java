package com.el.code;

import com.spring.ApplicationContext;
import com.spring.bean.Privilege;
import com.spring.serviceImpl.SecurityService;
import com.spring.utils.WebApplicationContextUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/23 16:14
 */
public class FunctionsEl{
    public static String elEncode(HttpServletRequest request,Integer id,String name,String uri,String desc) {
        if (!StringUtils.isAllBlank(name,uri,desc)&&id!=null){
            ApplicationContext applicationContext = WebApplicationContextUtils.getApplicationContext(request.getServletContext());
            SecurityService securityService = (SecurityService) applicationContext.getBean("securityService");
            Integer integer = securityService.updateSecurity(new Privilege(id, name, uri, desc));
            System.out.println("integer = " + integer);
            return integer>0?"修改成功":"修改失败";
        }
            return "空参";
    }
}
