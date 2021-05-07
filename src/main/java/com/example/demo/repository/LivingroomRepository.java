package com.example.demo.repository;

import com.example.demo.model.LivingroomCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivingroomRepository extends CategoryRepository<LivingroomCategory, Integer> {
}
