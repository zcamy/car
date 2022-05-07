package com.zhang.carservice.dao;

import com.zhang.carservice.entity.UserEntity;
import com.zhang.carservice.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    @Autowired
    UserMapper userMapper;

    /**
     * 查询所有用户信息
     * null 代表查询所有
     * @return 用户信息集合
     */
    public List<UserEntity> findAllUser(){
        return userMapper.selectList(null);
    }

    /**
     * 查询单个用户信息
     * @param userId 用户id
     * @return 用户信息
     */
    public UserEntity findOneUser(String userId){
        return userMapper.selectById(userId);
    }

    /**
     * 创建用户
     * @param userEntity 用户信息
     * @return 执行状态
     */
    public int createUser(UserEntity userEntity){
        return  userMapper.insert(userEntity);
    }

    /**
     * 更新用户信息
     * @param userEntity 用户信息
     * @return 执行状态
     */
    public int updateUser(UserEntity userEntity){
        return  userMapper.updateById(userEntity);
    }

    /**
     * 删除用户
     * @param userId 用户id
     * @return 执行状态
     */
    public int deleteUser(String userId){
        return  userMapper.deleteById(userId);
    }
}
