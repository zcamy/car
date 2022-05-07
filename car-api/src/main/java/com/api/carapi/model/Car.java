package com.api.carapi.model;


import com.api.carapi.annotations.Model;
import com.api.carapi.annotations.ModelParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 汽车信息表
 */

@Model(entity = "CarEntity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    //车辆id
    @ModelParam(value = "carId",desc = "车辆id")
    private String carId;

    //车牌名
    @ModelParam(value = "carName",desc = "车牌号")
    private String carName;

    //车辆价格
    @ModelParam(value = "carPrice",desc = "车辆价格")
    private int carPrice;

    //车辆状态
    @ModelParam(value = "carState",desc = "车辆状态")
    private int carState;

    //车辆图片
    @ModelParam(value = "carPhoto1",desc = "车辆图片")
    private String carPhoto1;

    //车辆图片
    @ModelParam(value = "carPhoto2",desc = "车辆图片")
    private String carPhoto2;

    //用户id
    @ModelParam(value = "userId",desc = "用户id")
    private String userId;

    //租车状态
    @ModelParam(value = "carUser",desc = "租车状态")
    private int carUser;

    //车辆类型
    @ModelParam(value = "carType",desc = "车辆类型")
    private int carType;

    //车牌号
    @ModelParam(value = "carCart",desc = "车牌号")
    private String carCart;

    //车辆地点
    @ModelParam(value = "carAddress",desc = "车辆地点")
    private String carAddress;

    //审核信息
    @ModelParam(value = "carRemark",desc = "审核信息")
    private String carRemark;

}
