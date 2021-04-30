package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    List<BedroomItem> getAllBedroomItems(){
        return itemService.getAllBedroomItems();
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/items/list/allKitchen")
    List<KitchenItem> getAllKitchenItems(){
        return itemService.getAllKitchenItems();
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/items/list/allLibrary")
    List<LibraryItem> getAllLibraryItems(){
        return itemService.getAllLibraryItems();
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/items/list/allLivingroom")
    List<LivingroomItem> getAllLivingroomItems(){
        return itemService.getAllLivingroomItems();
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/items/list/allStorage")
    List<StorageItem> getAllStorageItems(){
        return itemService.getAllStorageItems();
    }


    // 해당 cateNo의 thisCateNo로 모든 아이템 조회
    // ex. 침실가구-협탁
    @PreAuthorize("permitAll()")
    @GetMapping("/items/list/{category}")
    List<Item> getCategoryItemBySubcateNo(@RequestParam(value="subcateNo") String subCateNo, @PathVariable String category){
        return itemService.getCategoryItemBySubcateNo(subCateNo, category);
    }




    // 기본키로 아이템 조회
    /*
    @PreAuthorize("permitAll()")
    @GetMapping("/items")
    List<BedroomItem> getItemsByPK(@RequestParam(value="pdNo") int pdNo,
                                          @RequestParam(value="cateNo") String cateNo,
                                          @RequestParam(value="thisCateNo") String thisCateNo) {
        return itemService.getAllItemsByPK(pdNo, cateNo, thisCateNo);
    }

     */


    @PreAuthorize("permitAll()")
    @GetMapping("/items/getItem")
    ResponseEntity<Item> getAllCategoryItemsByPK(@RequestParam(value="pdNo") int pdNo,
                                                 @RequestParam(value="cateNo") String cateNo,
                                                 @RequestParam(value="subcateNo") String subcateNo){
        return itemService.getAllCategoryItemsByPK(pdNo, cateNo, subcateNo);
    }

    //상품 통합검색(카테고리 상관x)
    @PreAuthorize("permitAll()")
    @GetMapping("/items/searchAll")
    List<Item> searchItems(@RequestParam(value="keyword") String searchKeyword) {

        return itemService.searchAllItems(searchKeyword);
    }

    //카테별 검색
    @PreAuthorize("permitAll()")
    @GetMapping("/items/search")
    List<Item> searchCateItems(@RequestParam(value = "keyword")String searchKeyword,
                               @RequestParam(value="category")String category,
                               @RequestParam(value="subcateNo") String subcateNo){
        return itemService.searchCateItem(category, subcateNo, searchKeyword);
    }


}