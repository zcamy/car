package com.zhang.carservice.service;


import com.api.carapi.model.Car;
import com.api.carapi.model.Orders;
import com.api.carapi.service.CarService;
import com.api.carapi.service.OrdersService;
import com.api.carapi.service.UserService;
import com.zhang.carservice.config.BeanMapper.BeanMapper;
import com.zhang.carservice.dao.OrdersDao;
import com.zhang.carservice.entity.OrdersEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    OrdersDao OrdersDao;

    @Autowired
    CarService carService;

    @Autowired
    UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(OrdersServiceImpl.class);

    @Override
    public List<Orders> findAll() {
        List<OrdersEntity> ordersDaoList = OrdersDao.findAllOrders();
        List<Orders> ordersList = new ArrayList<>();
        for (OrdersEntity ordersEntity : ordersDaoList) {
            Orders map = BeanMapper.map(ordersEntity, Orders.class);
            ordersList.add(map);
        }
        return ordersList;
    }

    @Override
    public List<Orders> findAll(String userId) {
        List<Orders> all = findAll();
        List<Orders> ordersList = new ArrayList<>();
        if (all.size() != 0){
            for (Orders orders : all) {
                if (orders.getUserId().equals(userId)){
                    ordersList.add(orders);
                }
            }
            return ordersList;
        }
    return null;
    }

    @Override
    public Orders findOne(String ordersId) {
        OrdersEntity ordersEntity = OrdersDao.findOneOrders(ordersId);
        Orders orders = BeanMapper.map(ordersEntity, Orders.class);
        if (orders.getCarId()!= null){
           orders.setCar(carService.findOne(orders.getCarId()));
        }
        if (orders.getUserId() != null){
            orders.setUser(userService.findOne(orders.getUserId()));
        }
        return orders;
    }

    @Override
    public Orders findOneOrders(String userId) {
        List<Orders> all = findAll();
        if (all.size() != 0){
            for (Orders orders : all) {
                if (orders.getUserId() != null){
                    if (orders.getUserId().equals(userId) && orders.getOrderState() == 1){
                        orders.setUser(userService.findOne(orders.getUserId()));
                        orders.setCar(carService.findOne(orders.getCarId()));
                        return orders;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Orders findAllOrders(String userId) {
        List<Orders> all = findAll();
        if (all.size() != 0){
            for (Orders orders : all) {
                if (orders.getUserId() != null){
                    if (orders.getUserId().equals(userId) && orders.getOrderPayState() == 1){
                        orders.setUser(userService.findOne(orders.getUserId()));
                        orders.setCar(carService.findOne(orders.getCarId()));
                        return orders;
                    }
                }
            }
        }
        return null;
    }


    @Override
    public int createOrders(Orders orders){
        orders.setOrderCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        orders.setOrderPayState(1);
        orders.setOrderOverTime(getTime(orders));
        orders.setOrderPrice(getPrice(orders));
        Car car = carService.findOne(orders.getCarId());
        car.setCarState(1);
        carService.updateCar(car);
        OrdersEntity ordersEntity = BeanMapper.map(orders, OrdersEntity.class);
        return OrdersDao.createOrders(ordersEntity);
    }

    @Override
    public int updateOrders(Orders orders){
        OrdersEntity ordersEntity = BeanMapper.map(orders, OrdersEntity.class);
        return OrdersDao.updateOrders(ordersEntity);
    }

    @Override
    public int updateOrders(String orderId,int orderPayState){
        Orders orders = findOne(orderId);
        if (orders != null){
            if (orderPayState == 1){
                Car car = orders.getCar();
                car.setCarUser(1);
                carService.updateCar(car);
            }
            orders.setOrderPayState(orderPayState);
            if (orderPayState == 2){
                orders.setOrderState(1);
            }
            if (orderPayState == 3){
                orders.setOrderPayState(2);
                orders.setOrderState(0);
                Car car = orders.getCar();
                car.setCarUser(1);
                carService.updateCar(car);
            }
            return updateOrders(orders);
        }
        return 0;
    }

    @Override
    public int deleteOrders(String ordersId) {
        return OrdersDao.deleteOrders(ordersId);
    }

    /**
     * 计算租车价格
     * @param orders 订单信息
     * @return 价格
     */
    private int getPrice(Orders orders){
        Car car = carService.findOne(orders.getCarId());
        switch (orders.getOrderTimeLong()){
            case 1 :
                if (orders.getOrderTime() == 1){
                    return (int) (18* orders.getOrderDay()*0.8*car.getCarPrice());
                }else if (orders.getOrderTime() == 2){
                    return orders.getOrderDay()*car.getCarPrice();
                }
            case 2 :
                if (orders.getOrderTime() == 1){
                    return (int) (18 * 30 * orders.getOrderDay()*0.7*car.getCarPrice());
                }else if (orders.getOrderTime() == 2){
                    return (int) (18* orders.getOrderDay()*0.8*car.getCarPrice());
                }
        }
        return 0;
    }

    /**
     * 获取时间
     */
    private  String getTime(Orders orders){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        switch (orders.getOrderTime()){
            case 1 :
                if (orders.getOrderTimeLong() == 1){
                    calendar.add(Calendar.DAY_OF_MONTH, orders.getOrderDay());
                   return simpleDateFormat.format(calendar.getTime());
                }else if (orders.getOrderTimeLong() == 2){
                    calendar.add(Calendar.HOUR,  orders.getOrderDay());
                    return simpleDateFormat.format(calendar.getTime());
                }
            case 2 :
                if (orders.getOrderTimeLong() == 1){
                    calendar.add(Calendar.MONTH,  orders.getOrderDay());
                    return simpleDateFormat.format(calendar.getTime());
                }else if (orders.getOrderTimeLong() == 2){
                    calendar.add(Calendar.DAY_OF_MONTH,  orders.getOrderDay());
                    return simpleDateFormat.format(calendar.getTime());
                }
        }
        return null;
    }
}
