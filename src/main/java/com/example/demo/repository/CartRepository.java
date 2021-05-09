package com.example.demo.repository;

import com.example.demo.model.CartList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartList, Long> {

}
