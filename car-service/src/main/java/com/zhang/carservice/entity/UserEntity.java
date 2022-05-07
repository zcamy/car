package com.zhang.carservice.entity;

import com.api.carapi.annotations.Entity;
import com.api.carapi.model.User;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.jws.soap.SOAPBinding;

@Entity(model = User.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class UserEntity {

    //用户id
    @TableId(value = "user_id",type = IdType.ASSIGN_UUID)
    private String userId;

    //用户名
    @TableField(value = "user_username")
    private String userUsername;

    //用户名
    @TableField(value = "user_name")
    private String userName;

    //用户密码
    @TableField(value = "user_password")
    private String userPassword;

    //用户类型
    @TableField(value = "user_type")
    private String userType;

    //用户类型
    @TableField(value = "user_phone")
    private String userPhone;

    //用户类型
    @TableField(value = "user_car_number")
    private String userCarNumber;

    //用户类型
    @TableField(value = "user_cart")
    private String userCart;

}
