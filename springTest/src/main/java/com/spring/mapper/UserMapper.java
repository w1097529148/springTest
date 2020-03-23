package com.spring.mapper;

import com.spring.bean.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    public User findUserById(Integer id);
    public Integer insertUser(User user);
    public Integer updateUser(User user);
    public Integer deleteUser(Integer id);
    public List<User> queryUserAll(User user);
    public User login(Integer id,String username,String password);
    /**
    *@Description: 删除用户角色
    *@Params
    *@Return
    *@Author Mr.Li
    *@Date 2020/3/20 22:00
    */
    public Integer deleteUserRole(Integer userId);
    /**
    *@Description: 添加用户角色
    *@Params
    *@Return
    *@Author Mr.Li
    *@Date 2020/3/20 22:00
    */
    public Integer insertUserRole(Integer userId,Integer roleId);
}
