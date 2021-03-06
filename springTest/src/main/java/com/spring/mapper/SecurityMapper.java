package com.spring.mapper;

import com.Annotation.annotation.ioc.Repository;
import com.spring.bean.Page;
import com.spring.bean.PageBean;
import com.spring.bean.Privilege;

import java.util.List;
public interface SecurityMapper {
    public Privilege findSecurityById(Integer id);
    public Integer insertSecurity(Privilege privilege);
    public Integer updateSecurity(Privilege privilege);
    public Integer deleteSecurityById(Integer id);
    public List<Privilege> querySecurityAll(Privilege privilege);
    /**
    *@Description: 分页查询数据
    *@Params
    *@Return
    *@Author Mr.Li
    *@Date 2020/3/20 13:06
    */
    public List<Privilege> querySecurityPage(PageBean page);
    /**
    *@Description: 查询总记录
    *@Params
    *@Return
    *@Author Mr.Li
    *@Date 2020/3/20 13:08
    */
    public Integer querySecurityTotal();
    public List<Privilege> querySecurityByRoleId(Integer RoleId);
}
