package com.spring.serviceImpl;

import com.Annotation.annotation.ioc.Service;
import com.Annotation.annotation.security.security;
import com.spring.IService.ISecurityService;
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
@Service("SecurityService")
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
        @security("新增权限")
    @Override
    public Integer insertSecurity(Privilege privilege) {
        if (ObjectUtils.isNotEmpty(privilege))
          return  getMapper().insertSecurity(privilege);
        return null;

    }
        @security("修改权限")
    @Override
    public Integer updateSecurity(Privilege privilege) {
        if (ObjectUtils.isNotEmpty(privilege))
          return  getMapper().updateSecurity(privilege);
        return null;
    }
        @security("根据权限id删除权限")
    @Override
    public Integer deleteSecurityById(Integer id) {
        if (id!=null)
           return   getMapper().deleteSecurityById(id);
        return null;

    }
    @security("查询全部权限")
    @Override
    public List<Privilege> querySecurityAll(Privilege privilege) {
        if (ObjectUtils.isNotEmpty(privilege))
        return getMapper().querySecurityAll(privilege);
        return null;
    }
    @security(" 权限分页")
    @Override
    public List<Privilege> querySecurityPage(PageBean page) {
        return getMapper().querySecurityPage(page);
    }
    @security("查询权限总数")
    @Override
    public Integer querySecurityTotal() {
        return getMapper().querySecurityTotal();
    }
    @security("根据角色id查询权限")
    @Override
    public List<Privilege> querySecurityByRoleId(Integer RoleId) {
        if (RoleId!=null){
            return getMapper().querySecurityByRoleId(RoleId);
        }
        return null;
    }
}
