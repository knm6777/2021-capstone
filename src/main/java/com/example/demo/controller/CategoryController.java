package com.example.demo.controller;
import java.util.List;

import com.example.demo.model.category.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.CategoryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    // get big category
    @GetMapping("/category/big")
    List<FunitureCategory> getBigCategory() {
        return categoryService.getAllCategory();
    }

    // get small category
    @GetMapping("/category/small/bedroom")
    List<BedroomCategory> getBedroomCategory() {
        return categoryService.getAllBedroomCateNo();
    }

    @GetMapping("/category/small/kitchen")
    List<KitchenCategory> getKitchenCategory() {
        return categoryService.getAllKitchenCateNo();
    }

    @GetMapping("/category/small/library")
    List<LibraryCategory> getLibraryCategory() {
        return categoryService.getAllLibraryCateNo();
    }

    @GetMapping("/category/small/livingroom")
    List<LivingroomCategory> getLivingroomCategory() {
        return categoryService.getAllLivingroomCateNo();
    }

    @GetMapping("/category/small/storage")
    List<StorageCategory> getStorageCategory() {
        return categoryService.getAllStorageCateNo();
    }
}