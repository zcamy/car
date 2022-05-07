package com.api.carapi.service;

import com.api.carapi.model.User;

import java.util.List;

public interface UserService {
    /**
     * 查询所有用户信息
     * @return 用户信息集合
     */
    List<User> findAll() ;

    /**
     *
     * 根据id查询用户信息
     * @param userId 用户ID
     * @return 用户信息
     */
    User findOne(String userId)  ;

    /**
     * 创建用户
     * @param user 用户信息
     * @return 受影响行
     */
    int createUser(User user) ;

    /**
     * 用户登录
     * @param userUsername 用户名
     * @param password 密码
     * @return 用户信息
     */
    User userLogin(String userUsername ,String password);

    /**
     * 更新用户信息
     * @param user 用户信息
     * @return 受影响行
     */
    int  updateUser(User user) ;

    /**
     * 删除用户
     * @param userId 用户id
     * @return 受影响行
     */
    int  deleteUser(String userId);
}
