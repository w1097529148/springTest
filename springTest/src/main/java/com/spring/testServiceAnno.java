package com.spring;

import com.spring.bean.Page;
import com.spring.bean.Privilege;
import com.spring.mapper.RoleMapper;
import com.spring.mapper.SecurityMapper;
import com.spring.serviceImpl.RoleServiceImpl;
import com.spring.serviceImpl.SecurityService;
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
        roleService = (RoleServiceImpl) applicationContext.getBean("roleServiceImpl");

        RoleMapper mapper = MybatisUtils.getMapper(RoleMapper.class);
        mapper.deleteRoleSecurityByRoleId(1);
    }
}
