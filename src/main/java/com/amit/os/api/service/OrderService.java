package com.amit.os.api.service;

import com.amit.os.api.common.Payment;
import com.amit.os.api.common.TransactionRequest;
import com.amit.os.api.common.TransactionResponse;
import com.amit.os.api.entity.Order;
import com.amit.os.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RestTemplate restTemplate;

    public TransactionResponse saveOrder(TransactionRequest request)
    {
        String response="";
        Order order=request.getOrder();
        Payment payment=request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
        //TODO Do a rest call
        Payment paymentResponse = restTemplate.postForObject("http://PAYMENT-SERVICE/payment/doPayment",payment, Payment.class);
        response= paymentResponse.getPaymentStatus().equalsIgnoreCase("success")?"Payment processing successful & order places": "There is a failure in payment order added to cart";
        orderRepository.save(order);
        return new TransactionResponse(order,paymentResponse.getAmount(),paymentResponse.getTransactionId(),response);
    }
}
