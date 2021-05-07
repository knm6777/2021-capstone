package com.example.demo.repository;

import com.example.demo.model.BedroomCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BedroomRepository extends CategoryRepository<BedroomCategory, Integer> {
}
