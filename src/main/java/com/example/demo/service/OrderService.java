package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // 주문 목록 저장하기
    // Crud
    public void createOrder(Order order) {
        orderRepository.save(order);
    }

}
