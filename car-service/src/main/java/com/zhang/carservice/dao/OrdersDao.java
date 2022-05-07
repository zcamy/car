package com.zhang.carservice.dao;


import com.zhang.carservice.entity.OrdersEntity;
import com.zhang.carservice.mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public class OrdersDao {
    
    @Autowired
    OrdersMapper ordersMapper;

    /**
     * 查询所有订单信息
     * null 代表查询所有
     * @return 订单信息集合
     */
    public List<OrdersEntity> findAllOrders(){
        return ordersMapper.selectList(null);
    }

    /**
     * 查询单个订单信息
     * @param ordersId 订单id
     * @return 订单信息
     */
    public OrdersEntity findOneOrders(String ordersId){
        return ordersMapper.selectById(ordersId);
    }

    /**
     * 创建订单
     * @param ordersEntity 订单信息
     * @return 执行状态
     */
    public int createOrders(OrdersEntity ordersEntity){
        return  ordersMapper.insert(ordersEntity);
    }

    /**
     * 更新订单信息
     * @param ordersEntity 订单信息
     * @return 执行状态
     */
    public int updateOrders(OrdersEntity ordersEntity){
        return  ordersMapper.updateById(ordersEntity);
    }

    /**
     * 删除订单
     * @param ordersId 订单id
     * @return 执行状态
     */
    public int deleteOrders(String ordersId){
        return  ordersMapper.deleteById(ordersId);
    }
}
