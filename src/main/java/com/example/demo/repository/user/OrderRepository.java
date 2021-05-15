package com.example.demo.repository.user;

import com.example.demo.model.user.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
