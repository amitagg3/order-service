package com.amit.os.api.controller;

import com.amit.os.api.common.Payment;
import com.amit.os.api.common.TransactionRequest;
import com.amit.os.api.entity.Order;
import com.amit.os.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/bookOrder")
    public Order bookOrder(@RequestBody TransactionRequest
                                       request){
        Order order=request.getOrder();
        Payment payment=request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
        return orderService.saveOrder(order);
        //do a rest call to payment and pass the order id
    }
}
