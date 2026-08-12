package com.henriquefestraits.order_api.service;

import java.util.List;

import com.henriquefestraits.order_api.enums.OrderStatus;
import com.henriquefestraits.order_api.model.Order;

public interface OrderService {
    

    Order createOrder(Order order);

    void updateOrderStatus(Long orderId, OrderStatus status);

    void deleteOrder(Long orderId);

    Order getOrderById(Long orderId);

    List<Order> getAllOrders();

}
