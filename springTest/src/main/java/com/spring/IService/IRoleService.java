package com.spring.IService;

import com.spring.bean.Role;

import java.util.List;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/19 15:53
 */
public interface IRoleService {
    public Role findRoleById(Integer id);
    public Integer insertRole(Role Role);
    public Integer updateRole(Role Role);
    public Integer deleteRole(Integer id);
    public List<Role> queryRoleAll(Role role);
    //修改角色权限
    public Integer updateRoleSecurity(Integer roleId,List<Integer> securityIds);
}
