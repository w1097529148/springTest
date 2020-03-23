package com.spring;

import com.spring.bean.Page;
import com.spring.bean.Privilege;
import com.spring.bean.User;
import com.spring.mapper.RoleMapper;
import com.spring.mapper.SecurityMapper;
import com.spring.serviceImpl.RoleServiceImpl;
import com.spring.serviceImpl.SecurityService;
import com.spring.serviceImpl.UserServiceImpl;
import com.spring.utils.MybatisUtils;
import org.junit.Test;

import java.util.List;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/18 10:16
 */
public class testServiceAnno {
    private RoleServiceImpl roleService;
    @Test
    public void test(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
       UserServiceImpl userServiceImpl = (UserServiceImpl) applicationContext.getBean("userServiceImpl");
        SecurityService securityService= (SecurityService) applicationContext.getBean("securityService");
        List<Privilege> privileges = securityService.querySecurityAll(new Privilege(2, null, null, null));
        System.out.println("privileges = " + privileges);
        List<User> users = userServiceImpl.queryUserAll(new User(1, "1", "1", null));
        System.out.println("users = " + users);
        List<Privilege> privileges1 = securityService.querySecurityAll(new Privilege(null,null,null,null));
        System.out.println("privileges1 = " + privileges1);
    }
}
