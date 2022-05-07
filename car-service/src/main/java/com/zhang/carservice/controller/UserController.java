package com.zhang.carservice.controller;


import com.api.carapi.annotations.Log;
import com.api.carapi.model.User;
import com.api.carapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Log
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @RequestMapping(path="/createUser",method = RequestMethod.POST)
    public int createUser( @Valid @NotNull User user) {
      return  userService.createUser(user);
    }

    @RequestMapping(path="/findAllUser",method = RequestMethod.POST)
    public List<User> findAllUser() {
        return userService.findAll();
    }

    @RequestMapping(path="/findOneUser",method = RequestMethod.POST)
    public User findOneUser(@NotNull String userId) {
        return userService.findOne(userId);
    }

    @RequestMapping(path="/userLogin",method = RequestMethod.POST)
    public User userLogin(@NotNull String userUsername,@NotNull String userPassword) {
        return userService.userLogin(userUsername,userPassword);
    }

    @RequestMapping(path="/deleteUser",method = RequestMethod.POST)
    public int deleteUser(@NotNull String userId) {
        return userService.deleteUser(userId);
    }

    @RequestMapping(path="/updateUser",method = RequestMethod.POST)
    public int updateUser( @Valid @NotNull User user)  {
        return userService.updateUser(user);
    }

}
