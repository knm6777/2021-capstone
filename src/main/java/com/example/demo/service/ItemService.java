package com.example.demo.service;


import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private BedroomItemRepository bedroomItemRepository;
    @Autowired
    private KitchenItemRepository kitchenItemRepository;
    @Autowired
    private LibraryItemRepository libraryItemRepository;
    @Autowired
    private LivingroomItemRepository livingroomItemRepository;
    @Autowired
    private StorageItemRepository storageItemRepository;



    // cateNo 별 전제품 찾기 ex.침실가구...
    public List<BedroomItem> getAllBedroomItems() {
        return bedroomItemRepository.findAll();
    }

    public List<KitchenItem> getAllKitchenItems() {
        return kitchenItemRepository.findAll();
    }

    public List<LibraryItem> getAllLibraryItems() {
        return libraryItemRepository.findAll();
    }

    public List<LivingroomItem> getAllLivingroomItems() {
        return livingroomItemRepository.findAll();
    }

    public List<StorageItem> getAllStorageItems() {
        return storageItemRepository.findAll();
    }


    // subcateNo + cateNo 의 조합으로 제품들 찾기 ex.침실가구-협탁...
    public List<Item> getCategoryItemBySubcateNo(String subcateNo, String category){
        switch(category){
            case "bedroom":
                return bedroomItemRepository.getItemsBySubcate(subcateNo);
            case "kitchen":
                return kitchenItemRepository.getItemsBySubcate(subcateNo);
            case "library":
                return libraryItemRepository.getItemsBySubcate(subcateNo);
            case "livingroom":
                return livingroomItemRepository.getItemsBySubcate(subcateNo);
            case "storage":
                return storageItemRepository.getItemsBySubcate(subcateNo);
            default:
                return null;
        }
    }



    // 작은 카테고리 각각 기본키로 제품 찾기기
    // 이것도 위에도... abstract 쓰고싶은데.... 우선 급하니까...
    public ResponseEntity<Item> getAllCategoryItemsByPK(int pdNo, String cateNo, String subcateNo) {
        switch (cateNo) {
            case "침실가구":
                return ResponseEntity.ok(bedroomItemRepository.findByPK(pdNo, cateNo, subcateNo));
            case "주방가구":
                return ResponseEntity.ok(kitchenItemRepository.findByPK(pdNo, cateNo, subcateNo));
            case "서재/사무용가구":
                return ResponseEntity.ok(libraryItemRepository.findByPK(pdNo, cateNo, subcateNo));
            case "거실가구":
                return ResponseEntity.ok(livingroomItemRepository.findByPK(pdNo, cateNo, subcateNo));
            case "수납가구":
                return ResponseEntity.ok(storageItemRepository.findByPK(pdNo, cateNo, subcateNo));
            default:
                return null;
        }

    }


    //카테고리별 검색
    public List<Item> searchCateItem(String category, String subcateNo, String searchKeyword){
        switch (category) {
            case "침실가구":
                return bedroomItemRepository.searchItemByCate(subcateNo, searchKeyword);
            case "주방가구":
                return kitchenItemRepository.searchItemByCate(subcateNo, searchKeyword);
            case "서재/사무용가구":
                return libraryItemRepository.searchItemByCate(subcateNo, searchKeyword);
            case "거실가구":
                return livingroomItemRepository.searchItemByCate(subcateNo, searchKeyword);
            case "수납가구":
                return storageItemRepository.searchItemByCate(subcateNo, searchKeyword);
            default:
                return null;
        }
    }

    //모든 카테 통합검색
    public List<Item> searchAllItems(String searchKeyword){
        List<Item> itemList = new ArrayList<>();
        itemList.addAll(bedroomItemRepository.searchAllItem(searchKeyword));
        itemList.addAll(kitchenItemRepository.searchAllItem(searchKeyword));
        itemList.addAll(libraryItemRepository.searchAllItem(searchKeyword));
        itemList.addAll(livingroomItemRepository.searchAllItem(searchKeyword));
        itemList.addAll(storageItemRepository.searchAllItem(searchKeyword));

        return itemList;
    }




}
