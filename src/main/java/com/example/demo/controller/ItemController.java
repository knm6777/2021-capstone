package com.example.demo.controller;

import com.example.demo.model.item.*;
import com.example.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//itemcontroller
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ItemController {
    @Autowired
    private ItemService itemService;


    // 해당 카테고리 모든 아이템 조회
    // ex. 침실가구
    @PreAuthorize("permitAll()")
    @GetMapping("/items/list/allBedroom")
    ResponseEntity<List<BedroomItem>> getAllBedroomItems(){
        return ResponseEntity.ok(itemService.getAllBedroomItems());
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/items/list/allKitchen")
    ResponseEntity<List<KitchenItem>> getAllKitchenItems(){
        return ResponseEntity.ok(itemService.getAllKitchenItems());
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/items/list/allLibrary")
    ResponseEntity<List<LibraryItem>> getAllLibraryItems(){
        return ResponseEntity.ok(itemService.getAllLibraryItems());
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/items/list/allLivingroom")
    ResponseEntity<List<LivingroomItem>> getAllLivingroomItems(){
        return ResponseEntity.ok(itemService.getAllLivingroomItems());
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/items/list/allStorage")
    ResponseEntity<List<StorageItem>> getAllStorageItems(){
        return ResponseEntity.ok(itemService.getAllStorageItems());
    }


    // 해당 cateNo의 thisCateNo로 모든 아이템 조회
    // ex. 침실가구-협탁
    @PreAuthorize("permitAll()")
    @GetMapping("/items/list/{category}")
    ResponseEntity<List<Item>> getCategoryItemBySubcateNo(@RequestParam(value="subcateNo") String subCateNo, @PathVariable String category){
        return ResponseEntity.ok(itemService.getCategoryItemBySubcateNo(subCateNo, category));
    }


    // 기본키로 아이템 조회
    @PreAuthorize("permitAll()")
    @GetMapping("/items/getItem")
    ResponseEntity<Item> getAllCategoryItemsByPK(@RequestParam(value="pdNo") int pdNo,
                                                 @RequestParam(value="cateNo") String cateNo,
                                                 @RequestParam(value="subcateNo") String subcateNo){
        return ResponseEntity.ok(itemService.getAllCategoryItemsByPK(pdNo, cateNo, subcateNo));
    }

    //상품 통합검색(카테고리 상관x)
    @PreAuthorize("permitAll()")
    @GetMapping("/items/searchAll")
    ResponseEntity<List<Item>> searchItems(@RequestParam(value="keyword") String searchKeyword) {

        return ResponseEntity.ok(itemService.searchAllItems(searchKeyword));
    }

    //카테별 검색
    @PreAuthorize("permitAll()")
    @GetMapping("/items/search")
    ResponseEntity<List<Item>> searchCateItems(@RequestParam(value = "keyword")String searchKeyword,
                               @RequestParam(value="category")String category,
                               @RequestParam(value="subcateNo") String subcateNo){
        return ResponseEntity.ok(itemService.searchCateItem(category, subcateNo, searchKeyword));
    }


}