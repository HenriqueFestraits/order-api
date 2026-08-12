package com.henriquefestraits.order_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.henriquefestraits.order_api.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
