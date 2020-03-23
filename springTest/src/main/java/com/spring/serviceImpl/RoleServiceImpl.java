package com.spring.serviceImpl;

import com.spring.IService.IRoleService;
import com.spring.bean.Role;
import com.spring.mapper.RoleMapper;
import com.spring.utils.MybatisUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/19 15:57
 */
public class RoleServiceImpl implements IRoleService {
    private RoleMapper getMapper(){
        return MybatisUtils.getMapper(RoleMapper.class);
    }
    @Override
    public Role findRoleById(Integer id) {
        if (id!=null)
        return getMapper().findRoleById(id);
        return null;
    }

    @Override
    public Integer insertRole(Role Role) {
        if (ObjectUtils.isNotEmpty(Role))
           return getMapper().insertRole(Role);
        return null;
    }

    @Override
    public Integer updateRole(Role Role) {
        if (ObjectUtils.isNotEmpty(Role))
          return  getMapper().updateRole(Role);
        return null;
    }

    @Override
    public Integer deleteRole(Integer id) {
        if (id!=null)
           return getMapper().deleteRole(id);
        return null;
    }

    @Override
    public List<Role> queryRoleAll(Role role) {
        return getMapper().queryRoleAll(role);
    }

    /**
    *@Description: 修改角色中间表
    *@Params
    *@Return
    *@Author Mr.Li
    *@Date 2020/3/20 21:42
    */
    @Override
    public Integer updateRoleSecurity(Integer roleId, List<Integer> securityIds) {
        if (roleId==null)
            return null;

        if (securityIds!=null)
        for (Integer securityId : securityIds) {
           return getMapper().insertRoleSecurity(roleId,securityId);
        }
        return getMapper().deleteRoleSecurityByRoleId(roleId);
    }
}
