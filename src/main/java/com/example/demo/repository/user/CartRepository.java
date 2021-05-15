package com.example.demo.repository.user;

import com.example.demo.model.user.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    public List<Cart> findAllByUserId(String id);
    public Cart findByCartNo(Long CartNo);
    public Cart findByUserIdAndPdNoAndSubcateNoAndCategoryNo(String userId, Integer pdNo, String subcateNo, String categoryNo);
    public void deleteAllByUserId(String userId);
}
