package com.henriquefestraits.order_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PatchMapping;

import com.henriquefestraits.order_api.enums.OrderStatus;
import com.henriquefestraits.order_api.model.Order;
import com.henriquefestraits.order_api.service.OrderService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/orders")
public class OrderRestController {
    
    @Autowired
    private  OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status/{status}")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable Long id, @PathVariable OrderStatus status) {
        orderService.updateOrderStatus(id, status);
        return ResponseEntity.noContent().build();
    }
}
