package com.zhang.carservice.entity;

import com.api.carapi.annotations.Entity;
import com.api.carapi.model.Orders;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity(model = Orders.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("orders")
public class OrdersEntity {

    //订单id
    @TableId(value = "order_id",type = IdType.ASSIGN_UUID)
    private String orderId;

    //开始时间
    @TableField(value = "order_create_time")
    private String orderCreateTime;

    // 订单时长
    @TableField(value = "order_time")
    private int orderTime;

    // 订单时长
    @TableField(value = "order_time_long")
    private int orderTimeLong;

    // 天数
    @TableField(value = "order_day")
    private int orderDay;

    //结束时间
    @TableField(value = "order_over_time")
    private String orderOverTime;

    //订单价格
    @TableField(value = "order_price")
    private int orderPrice;

    //订单状态
    @TableField(value = "order_state")
    private int orderState;

    // 用户id
    @TableField(value = "user_id")
    private String userId;

    //汽车id
    @TableField(value = "car_id")
    private String carId;

    //订单支付状态
    @TableField(value = "order_pay_state")
    private int orderPayState;
}
