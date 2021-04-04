package com.example.demo.service;


import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // get boards data
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }


}
