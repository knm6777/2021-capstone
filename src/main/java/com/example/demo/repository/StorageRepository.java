package com.example.demo.repository;

import com.example.demo.model.StorageCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageRepository extends CategoryRepository<StorageCategory, Integer> {
}
