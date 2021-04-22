package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/items/list/bedroom")
    List<BedroomItem> getBedroomItemsByThisCateNo(@RequestParam(value="thisCateNo") String thisCateNo){
        return itemService.getBedroomItemsByThisCateNo(thisCateNo);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/items/list/kitchen")
    List<KitchenItem> getKitchenItemsByThisCateNo(@RequestParam(value="thisCateNo")String thisCateNo){
        return itemService.getKitchenItemsByThisCateNo(thisCateNo);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/items/list/library")
    List<LibraryItem> getLibraryItemsByThisCateNo(@RequestParam(value="thisCateNo")String thisCateNo){
        return itemService.getLibraryItemsByThisCateNo(thisCateNo);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/items/list/livingroom")
    List<LivingroomItem> getLivingroomItemsByThisCateNo(@RequestParam(value="thisCateNo")String thisCateNo){
        return itemService.getLivingroomItemsByThisCateNo(thisCateNo);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/items/list/storage")
    List<StorageItem> getStorageItemsByThisCateNo(@RequestParam(value="thisCateNo")String thisCateNo){
        return itemService.getStorageItemsByThisCateNo(thisCateNo);
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
    @GetMapping("/items/bedroom")
    List<BedroomItem> getBedroomItemsByPK(@RequestParam(value="pdNo") int pdNo,
                                          @RequestParam(value="cateNo") String cateNo,
                                          @RequestParam(value="thisCateNo") String thisCateNo) {
        return itemService.getAllBedroomItemsByPK(pdNo, cateNo, thisCateNo);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/items/kitchen")
    List<KitchenItem> getKitchenItemsByPK(@RequestParam(value="pdNo") int pdNo,
                                          @RequestParam(value="cateNo") String cateNo,
                                          @RequestParam(value="thisCateNo") String thisCateNo) {
        return itemService.getAllKitchenItemsByPK(pdNo, cateNo, thisCateNo);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/items/library")
    List<LibraryItem> getLibraryItemsByPK(@RequestParam(value="pdNo") int pdNo,
                                          @RequestParam(value="cateNo") String cateNo,
                                          @RequestParam(value="thisCateNo") String thisCateNo) {
        return itemService.getAllLibraryItemsByPK(pdNo, cateNo, thisCateNo);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/items/livingroom")
    List<LivingroomItem> getLivingroomItemsByPK(@RequestParam(value="pdNo") int pdNo,
                                                @RequestParam(value="cateNo") String cateNo,
                                                @RequestParam(value="thisCateNo") String thisCateNo) {
        return itemService.getAllLivingroomItemsByPK(pdNo, cateNo, thisCateNo);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/items/storage")
    List<StorageItem> getStorageItemsByPK(@RequestParam(value="pdNo") int pdNo,
                                          @RequestParam(value="cateNo") String cateNo,
                                          @RequestParam(value="thisCateNo") String thisCateNo) {
        return itemService.getAllStorageItemsByPK(pdNo, cateNo, thisCateNo);
    }



}