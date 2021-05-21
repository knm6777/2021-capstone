package com.example.demo.repository.user;

import com.example.demo.model.user.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    public List<Purchase> findAllByUserId(String id);
    public List<Purchase> findAllByPurchaseDate(LocalDateTime purDate);
    public Purchase findByPurchaseNo(Long purNo);
    public void deleteAllByUserId(String userId);
    public void deleteAllByPurchaseDate(LocalDateTime purDate);
}
