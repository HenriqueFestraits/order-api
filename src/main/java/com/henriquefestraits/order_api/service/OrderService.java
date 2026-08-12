package com.henriquefestraits.order_api.service;

import java.util.List;

import com.henriquefestraits.order_api.enums.OrderStatus;
import com.henriquefestraits.order_api.model.Order;
import com.henriquefestraits.order_api.model.OrderRequest;

public interface OrderService {
    

    Order createOrder(OrderRequest orderRequest);

    void updateOrderStatus(Long orderId, OrderStatus status);

    void deleteOrder(Long orderId);

    Order getOrderById(Long orderId);

    List<Order> getAllOrders();

}
