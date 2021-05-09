package com.example.demo.repository;

import com.example.demo.model.PurchaseList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<PurchaseList, Long> {

}
