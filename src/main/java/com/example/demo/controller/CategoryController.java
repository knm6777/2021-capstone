package com.example.demo.controller;
import java.util.List;
import java.util.Map;

import com.example.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @PreAuthorize("permitAll()")
    @GetMapping("/category/big")
    List<FunitureCategory> getBigCategory() {
        return categoryService.getAllCategory();
    }

    // get small category
    @PreAuthorize("permitAll()")
    @GetMapping("/category/small/bedroom")
    List<BedroomCategory> getBedroomCategory() {
        return categoryService.getAllBedroomCateNo();
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/category/small/kitchen")
    List<KitchenCategory> getKitchenCategory() {
        return categoryService.getAllKitchenCateNo();
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/category/small/library")
    List<LibraryCategory> getLibraryCategory() {
        return categoryService.getAllLibraryCateNo();
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/category/small/livingroom")
    List<LivingroomCategory> getLivingroomCategory() {
        return categoryService.getAllLivingroomCateNo();
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/category/small/storage")
    List<StorageCategory> getStorageCategory() {
        return categoryService.getAllStorageCateNo();
    }
}