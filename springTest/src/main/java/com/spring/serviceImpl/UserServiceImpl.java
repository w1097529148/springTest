package com.spring.serviceImpl;

import com.spring.IService.IUserService;
import com.spring.bean.User;
import com.spring.mapper.UserMapper;
import com.spring.utils.MD5Utils;
import com.spring.utils.MybatisUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/19 15:56
 */
public class UserServiceImpl implements IUserService {
    private UserMapper getMapper(){
        return MybatisUtils.getMapper(UserMapper.class);
    }
    @Override
    public User findUserById(Integer id) {
        if(id!=null)
            return getMapper().findUserById(id);
        return null;
    }

    @Override
    public Integer insertUser(User user) {
        if (ObjectUtils.isNotEmpty(user)){
            user.setSalt(MD5Utils.md5Password(user.getUserPassword()));
           return getMapper().insertUser(user);
        }
            return null;
    }

    @Override
    public Integer updateUser(User user) {
        if (ObjectUtils.isNotEmpty(user)){
            user.setSalt(MD5Utils.md5Password(user.getUserPassword()));
           return getMapper().updateUser(user);
        }
        return null;
    }

    @Override
    public Integer deleteUser(Integer id) {
        if (id!=null)
           return getMapper().deleteUser(id);
        return null;

    }

    @Override
    public List<User> queryUserAll(User user) {
        if (ObjectUtils.isNotEmpty(user)){
           return getMapper().queryUserAll(user);
        }
        return null;
    }

    @Override
    public User login(String username, String password) {
        if (username==null||username.trim().equals(""))
        throw new RuntimeException("用户名为空");
        if (password==null||password.trim().equals(""))
            throw new RuntimeException("密码为空");
        User user=new User();
        user.setUserName(username);
        user.setUserPassword(password);
        user.setSalt(MD5Utils.md5Password(password));
        List<User> users = getMapper().queryUserAll(user);
        if (users!=null&&users.size()==1)
            return user;
        throw new RuntimeException("用户名或密码错误");
    }

    @Override
    public Integer updateUserRole(Integer userId, List<Integer> roleId) {
        if (userId==null)
            return null;

        if (roleId!=null) {
            for (Integer integer : roleId) {
                return getMapper().insertUserRole(userId,integer);
            }
        }
        return getMapper().deleteUser(userId);
    }
}
