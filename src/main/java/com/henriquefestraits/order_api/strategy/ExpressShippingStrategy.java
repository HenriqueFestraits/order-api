package com.henriquefestraits.order_api.strategy;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.henriquefestraits.order_api.enums.OrderType;
import com.henriquefestraits.order_api.model.OrderRequest;

@Component("EXPRESS")
public class ExpressShippingStrategy implements ShippingStrategy {

    @Override
    public BigDecimal calculateShippingCost(OrderRequest orderRequest) {

        if(orderRequest.getTotalAmount().compareTo(BigDecimal.valueOf(100.0)) >= 0) {
            return BigDecimal.TEN;
        }
        return BigDecimal.valueOf(20.00);
    }

    @Override
    public OrderType getOrderType() {
        return OrderType.EXPRESS;
    }
}
