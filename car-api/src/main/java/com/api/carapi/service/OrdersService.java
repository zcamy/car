package com.api.carapi.service;


import com.api.carapi.model.Orders;

import java.util.List;

public interface OrdersService {
    /**
     * 查询所有订单信息
     * @return 订单信息集合
     */
    List<Orders> findAll() ;

    /**
     * 查询用户所有订单
     * @param userId 用户id
     * @return 订单信息
     */
    List<Orders> findAll(String userId);

    /**
     *
     * 根据id查询订单信息
     * @param ordersId 订单ID
     * @return 订单信息
     */
    Orders findOne(String ordersId)  ;

    /**
     *查询订单
     * @param userId 用户id
     * @return 订单信息
     */
    Orders findOneOrders(String userId);

    /**
     * 订单状态
     * @param userId 用户id
     * @return 订单
     */
    Orders findAllOrders(String userId);

    /**
     * 创建订单
     * @param orders 订单信息
     * @return 受影响行
     */
    int createOrders(Orders orders) ;

    /**
     * 更新订单信息
     * @param orderId 订单id
     * @return 受影响行
     */
    int  updateOrders(Orders orderId) ;

    /**
     * 更新订单中状态
     * @param orderId 订单id
     * @return 状态
     */
    int  updateOrders( String orderId,int orderState) ;

    /**
     * 删除订单
     * @param ordersId 订单id
     * @return 受影响行
     */
    int  deleteOrders(String ordersId);
}
