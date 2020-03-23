package com.spring.mapper;

import com.spring.bean.Role;

import java.util.List;

public interface RoleMapper {
    public Role findRoleById(Integer id);
    public Integer insertRole(Role Role);
    public Integer updateRole(Role Role);
    public Integer deleteRole(Integer id);
    public List<Role> queryRoleAll(Role role);
    public Integer deleteRoleSecurityByRoleId(Integer roleId);
    public Integer insertRoleSecurity(Integer roleId, Integer securityId);
}
