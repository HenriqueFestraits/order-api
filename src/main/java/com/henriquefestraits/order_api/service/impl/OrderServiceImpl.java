package com.henriquefestraits.order_api.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henriquefestraits.order_api.enums.OrderStatus;
import com.henriquefestraits.order_api.model.Order;
import com.henriquefestraits.order_api.model.OrderRequest;
import com.henriquefestraits.order_api.repository.OrderRepository;
import com.henriquefestraits.order_api.service.OrderService;
import com.henriquefestraits.order_api.strategy.ShippingStrategy;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderRepository orderRepository;

    private final Map<String, ShippingStrategy> shippingStrategies;

    @Autowired
    public OrderServiceImpl(Map<String, ShippingStrategy> shippingStrategies) {
        this.shippingStrategies = shippingStrategies;
    }

    
    @Override
    public Order createOrder(OrderRequest orderRequest) {


        ShippingStrategy shippingStrategy = shippingStrategies.get(orderRequest.getOrderType().name());
        BigDecimal shippingCost = shippingStrategy.calculateShippingCost(orderRequest);
        Order order = new Order();
        order.setCustomerName(orderRequest.getCustomerName());
        order.setOrderType(orderRequest.getOrderType());
        order.setTotalAmount(orderRequest.getTotalAmount());
        order.setShippingCost(shippingCost);
        order.setFinalValue(calculateFinalValue(order));
        order.setOrderStatus(OrderStatus.PENDING);
        order.setCreatedAt(java.time.LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Override
    public void updateOrderStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setOrderStatus(status);
            orderRepository.save(order);
        }
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }



    private BigDecimal calculateFinalValue(Order order) {
        return order.getTotalAmount().add(order.getShippingCost());
    }

    
}
