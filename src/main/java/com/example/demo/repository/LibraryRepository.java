package com.example.demo.repository;

import com.example.demo.model.LibraryCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends CategoryRepository<LibraryCategory, Integer> {
}
