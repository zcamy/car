package com.zhang.carservice.entity;

import com.api.carapi.annotations.Entity;
import com.api.carapi.model.Car;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(model = Car.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("car")
public class CarEntity {

    //车辆id
    @TableId(value = "car_id",type = IdType.ASSIGN_UUID)
    private String carId;

    //车牌名
    @TableField(value = "car_name")
    private String carName;

    //车辆价格
    @TableField(value = "car_price")
    private int carPrice;

    //车辆状态
    @TableField(value = "car_state")
    private int carState;

    //车辆图片
    @TableField(value = "car_photo1")
    private String carPhoto1;

    //审核信息
    @TableField(value = "car_remark")
    private String carRemark;

    //用户id
    @TableField(value = "user_id")
    private String userId;

    //车辆类型
    @TableField(value = "car_type")
    private int carType;

    //车牌号
    @TableField(value = "car_cart")
    private String carCart;

    //车辆地点
    @TableField(value = "car_address")
    private String carAddress;

    //租车状态
    @TableField(value = "car_user")
    private int carUser;


}
