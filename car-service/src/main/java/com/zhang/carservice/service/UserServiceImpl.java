package com.zhang.carservice.service;

import com.api.carapi.model.User;
import com.api.carapi.service.UserService;
import com.zhang.carservice.config.BeanMapper.BeanMapper;
import com.zhang.carservice.dao.UserDao;
import com.zhang.carservice.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao UserDao;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public List<User> findAll() {
        List<UserEntity> userDaoList = UserDao.findAllUser();
        List<User> userList = new ArrayList<>();
        for (UserEntity userEntity : userDaoList) {
            User map = BeanMapper.map(userEntity, User.class);
            userList.add(map);
        }
        return userList;
    }

    @Override
    public User findOne(String userId) {
        UserEntity userEntity = UserDao.findOneUser(userId);
        return BeanMapper.map(userEntity, User.class);
    }

    @Override
    public int createUser(User user){
        UserEntity userEntity = BeanMapper.map(user, UserEntity.class);
        int i = UserDao.createUser(userEntity);
        if (i == 1){
            return 1;
        }
        return 0;
    }

    @Override
    public User userLogin(String userName ,String password){
        List<User> allUser = findAll();
        for (User user : allUser) {
            if (user.getUserUsername().equals(userName) && user.getUserPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    @Override
    public int updateUser(User user){
        UserEntity userEntity = BeanMapper.map(user, UserEntity.class);
        int i = UserDao.updateUser(userEntity);
        if (i == 1){
            return 1;
        }
        return 0;
    }



    @Override
    public int deleteUser(String userId) {
        int i = UserDao.deleteUser(userId);
        if (i == 1){
            return 1;
        }
        return 0;
    }
}
