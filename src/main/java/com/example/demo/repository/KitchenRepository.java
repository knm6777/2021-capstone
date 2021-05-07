package com.example.demo.repository;

import com.example.demo.model.KitchenCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KitchenRepository extends CategoryRepository<KitchenCategory, Integer> {
}
