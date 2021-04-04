package com.example.demo.controller;
import java.util.List;
import java.util.Map;

import com.example.demo.model.Comment;
import com.example.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    // get all category
    @PreAuthorize("permitAll()")
    @GetMapping("/category")

     List<Category> getAllCategory() {

        return categoryService.getAllCategory();
    }
}