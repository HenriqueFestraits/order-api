package com.henriquefestraits.order_api.model;

import com.henriquefestraits.order_api.enums.OrderStatus;
import com.henriquefestraits.order_api.enums.OrderType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    @Enumerated(EnumType.STRING)
    private OrderType orderType;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private BigDecimal totalAmount;
    private BigDecimal shippingCost;
    private BigDecimal finalValue;
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public OrderType getOrderType() {
        return orderType;
    }
    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    public BigDecimal getShippingCost() {
        return shippingCost;
    }
    public void setShippingCost(BigDecimal shippingCost) {
        this.shippingCost = shippingCost;
    }
    public BigDecimal getFinalValue() {
        return finalValue;
    }
    public void setFinalValue(BigDecimal finalValue) {
        this.finalValue = finalValue;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}


