package com.api.carapi.model;

import com.api.carapi.annotations.ModelParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    //用户id
    @ModelParam(value = "userId")
    private String userId;

    //用户姓名
    @ModelParam(value = "userName")
    private String userName;

    //用户名
    @ModelParam(value = "userUsername")
    private String userUsername;

    //用户密码
    @ModelParam(value = "userPassword")
    private String userPassword;

    //用户类型
    @ModelParam(value = "userType")
    private String userType;

    //用户类型
    @ModelParam(value = "userPhone")
    private String userPhone;

    //用户类型
    @ModelParam(value = "user_car_number")
    private String userCarNumber;

    //用户身份证号
    @ModelParam(value = "userCart")
    private String userCart;
}
