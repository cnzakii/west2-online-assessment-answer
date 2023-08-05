package com.cnzakii.orderManage.mapper;

import com.cnzakii.orderManage.entity.Order;

import java.util.List;

/**
 * 订单类的mapper接口
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-08-05
 **/
public interface OrderMapper {


    void insertOrder(Order order);


    void deleteOrderById(Integer id);


    Order selectOrderById(Integer id);

    List<Order> selectAll();
}
