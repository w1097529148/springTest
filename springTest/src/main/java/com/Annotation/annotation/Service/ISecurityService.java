package com.Annotation.annotation.Service;

import com.Annotation.annotation.ioc.Service;
import com.spring.bean.PageBean;
import com.spring.bean.Privilege;

import java.util.List;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/19 14:53
 */

public interface ISecurityService {
    public Privilege findSecurityById(Integer id);
    public Integer insertSecurity(Privilege privilege);
    public Integer updateSecurity(Privilege privilege);
    public Integer deleteSecurityById(Integer id);
    public List<Privilege> querySecurityAll(Privilege privilege);
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
