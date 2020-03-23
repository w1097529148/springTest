package com.spring.IService;

import com.spring.bean.User;

import java.util.List;

public interface IUserService {
    public User findUserById(Integer id);
    public Integer insertUser(User user);
    public Integer updateUser(User user);
    public Integer deleteUser(Integer id);
    public List<User> queryUserAll(User user);
    public User login(Integer id,String username,String password);
    /**
    *@Description: 修改用户角色中间表
    *@Params
    *@Return
    *@Author Mr.Li
    *@Date 2020/3/20 21:51
    */
    public Integer updateUserRole(Integer userId,List<Integer> roleId);
}
