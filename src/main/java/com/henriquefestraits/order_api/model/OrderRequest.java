package com.henriquefestraits.order_api.model;

import java.math.BigDecimal;

import com.henriquefestraits.order_api.enums.OrderType;

public class OrderRequest {
    
    private String customerName;
    private OrderType orderType;
    private BigDecimal totalAmount;


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
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
