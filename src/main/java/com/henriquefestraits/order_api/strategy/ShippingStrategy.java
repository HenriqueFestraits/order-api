package com.henriquefestraits.order_api.strategy;

import java.math.BigDecimal;

import com.henriquefestraits.order_api.enums.OrderType;
import com.henriquefestraits.order_api.model.Order;

public interface ShippingStrategy {
    
    BigDecimal calculateShippingCost(Order order);

    OrderType getOrderType();
}
