package com.api.carapi.model;


import com.api.carapi.annotations.ModelParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    //订单id
    @ModelParam(value = "orderId")
    private String orderId;

    //开始时间
    @ModelParam(value = "orderCreateTime")
    private String orderCreateTime;

    // 订单时长
    @ModelParam(value = "orderTime")
    private int orderTime;

    // 订单时长
    @ModelParam(value = "orderTimeLong")
    private int orderTimeLong;

    // 天数
    @ModelParam(value = "orderDay")
    private int orderDay;

    //结束时间
    @ModelParam(value = "orderOverTime")
    private String orderOverTime;

    //订单价格
    @ModelParam(value = "orderPrice")
    private int orderPrice;

    //订单状态
    @ModelParam(value = "orderState")
    private int orderState;

    //订单支付状态
    @ModelParam(value = "orderPayState")
    private int orderPayState;

    // 用户id
    @ModelParam(value = "userId")
    private String userId;

    //汽车id
    @ModelParam(value = "carId")
    private String carId;

    // 用户id
    @ModelParam(value = "user")
    private User user;

    // 用户id
    @ModelParam(value = "car")
    private Car car;
}
