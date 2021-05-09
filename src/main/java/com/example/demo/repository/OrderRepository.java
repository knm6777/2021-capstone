package com.example.demo.repository;

import com.example.demo.model.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderList, Long> {

}
