package com.amit.os.api.service;

import com.amit.os.api.entity.Order;
import com.amit.os.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }
}