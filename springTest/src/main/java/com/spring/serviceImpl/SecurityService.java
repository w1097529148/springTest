package com.spring.serviceImpl;

import com.spring.IService.ISecurityService;
import com.spring.bean.Page;
import com.spring.bean.PageBean;
import com.spring.bean.Privilege;
import com.spring.mapper.SecurityMapper;
import com.spring.utils.MybatisUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/19 14:43
 */
public class SecurityService implements ISecurityService {
    private SecurityMapper getMapper(){
        return MybatisUtils.getMapper(SecurityMapper.class);
    }

    @Override
    public Privilege findSecurityById(Integer id) {
        if (id!=null)
        return getMapper().findSecurityById(id);
        return null;
    }

    @Override
    public Integer insertSecurity(Privilege privilege) {
        if (ObjectUtils.isNotEmpty(privilege))
          return  getMapper().insertSecurity(privilege);
        return null;

    }

    @Override
    public Integer updateSecurity(Privilege privilege) {
        if (ObjectUtils.isNotEmpty(privilege))
          return  getMapper().updateSecurity(privilege);
        return null;
    }

    @Override
    public Integer deleteSecurityById(Integer id) {
        if (id!=null)
           return   getMapper().deleteSecurityById(id);
        return null;

    }

    @Override
    public List<Privilege> querySecurityAll(Privilege privilege) {
        if (ObjectUtils.isNotEmpty(privilege))
        return getMapper().querySecurityAll(privilege);
        return null;
    }

    @Override
    public List<Privilege> querySecurityPage(PageBean page) {
        return getMapper().querySecurityPage(page);
    }

    @Override
    public Integer querySecurityTotal() {
        return getMapper().querySecurityTotal();
    }

    @Override
    public List<Privilege> querySecurityByRoleId(Integer RoleId) {
        if (RoleId!=null){
            return getMapper().querySecurityByRoleId(RoleId);
        }
        return null;
    }
}
