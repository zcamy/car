package com.zhang.carservice.controller;

import com.api.carapi.annotations.Log;
import com.api.carapi.model.Orders;
import com.api.carapi.service.OrdersService;
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
@RequestMapping("/orders")
public class OrdersController {


    private static final Logger logger = LoggerFactory.getLogger(OrdersController.class);

    @Autowired
    OrdersService ordersService;

    @RequestMapping(path="/createOrders",method = RequestMethod.POST)
    public int createOrders(@Valid @NotNull Orders orders) {
        return  ordersService.createOrders(orders);
    }

    @RequestMapping(path="/findAllOrders",method = RequestMethod.POST)
    public List<Orders> findAllOrders(@NotNull String userId) {
        return ordersService.findAll(userId);
    }

    @RequestMapping(path="/findAll",method = RequestMethod.POST)
    public Orders findAll(@NotNull String userId) {
        return ordersService.findAllOrders(userId);
    }

    @RequestMapping(path="/findOne",method = RequestMethod.POST)
    public Orders findOne(@NotNull String orderId) {
        return ordersService.findOne(orderId);
    }

    @RequestMapping(path="/findOneOrders",method = RequestMethod.POST)
    public Orders findOneOrders(@NotNull String userId) {
        return ordersService.findOneOrders(userId);
    }

    @RequestMapping(path="/deleteOrders",method = RequestMethod.POST)
    public int deleteOrders(@NotNull String ordersId) {
        return ordersService.deleteOrders(ordersId);
    }

    @RequestMapping(path="/updateOrders",method = RequestMethod.POST)
    public int updateOrders(@NotNull String orderId,int orderPayState)  {
        return ordersService.updateOrders(orderId,orderPayState);
    }
}
