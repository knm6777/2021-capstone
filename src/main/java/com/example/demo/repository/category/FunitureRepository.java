package com.example.demo.repository.category;

import com.example.demo.model.category.FunitureCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FunitureRepository extends JpaRepository<FunitureCategory, Integer> {
    public List<FunitureCategory> findAll();
}
