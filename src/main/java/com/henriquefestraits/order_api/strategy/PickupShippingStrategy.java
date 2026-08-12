package com.henriquefestraits.order_api.strategy;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.henriquefestraits.order_api.enums.OrderType;
import com.henriquefestraits.order_api.model.OrderRequest;

@Component("PICKUP")
public class PickupShippingStrategy implements ShippingStrategy {

    @Override
    public BigDecimal calculateShippingCost(OrderRequest orderRequest) {
        return BigDecimal.ZERO;
    }
    
    @Override
    public OrderType getOrderType() {
        return OrderType.PICKUP;
    }
}
