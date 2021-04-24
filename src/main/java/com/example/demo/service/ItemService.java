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


    // thisCateNo + cateNo 의 조합으로 제품들 찾기 ex.침실가구-협탁...
    public List<BedroomItem> getBedroomItemsByThisCateNo(String thisCateNo) {
        return bedroomItemRepository.findAllByThisCateNo(thisCateNo);
    }

    public List<KitchenItem> getKitchenItemsByThisCateNo(String thisCateNo) {
        return kitchenItemRepository.findAllByThisCateNo(thisCateNo);
    }

    public List<LibraryItem> getLibraryItemsByThisCateNo(String thisCateNo) {
        return libraryItemRepository.findAllByThisCateNo(thisCateNo);
    }

    public List<LivingroomItem> getLivingroomItemsByThisCateNo(String thisCateNo) {
        return livingroomItemRepository.findAllByThisCateNo(thisCateNo);
    }

    public List<StorageItem> getStorageItemsByThisCateNo(String thisCateNo) {
        return storageItemRepository.findAllByThisCateNo(thisCateNo);
    }


    // 작은 카테고리 각각 기본키로 제품 찾기기
    // 이것도 위에도... abstract 쓰고싶은데.... 우선 급하니까...

    public ResponseEntity<BedroomItem> getAllBedroomItemsByPK(int pdNo, String cateNo, String thisCateNo) {
        BedroomItem bedroomItem = bedroomItemRepository.findItemByPdNoAndCateNoAndThisCateNo(pdNo, cateNo, thisCateNo);
        return ResponseEntity.ok(bedroomItem);
    }

    public ResponseEntity<KitchenItem> getAllKitchenItemsByPK(int pdNo, String cateNo, String thisCateNo) {
        KitchenItem kitchenItem = kitchenItemRepository.findItemByPdNoAndCateNoAndThisCateNo(pdNo, cateNo, thisCateNo);
        return ResponseEntity.ok(kitchenItem);
    }

    public ResponseEntity<LibraryItem> getAllLibraryItemsByPK(int pdNo, String cateNo, String thisCateNo) {
        LibraryItem libraryItem = libraryItemRepository.findItemByPdNoAndCateNoAndThisCateNo(pdNo, cateNo, thisCateNo);
        return ResponseEntity.ok(libraryItem);
    }

    public ResponseEntity<LivingroomItem> getAllLivingroomItemsByPK(int pdNo, String cateNo, String thisCateNo) {
        LivingroomItem livingroomItem = livingroomItemRepository.findItemByPdNoAndCateNoAndThisCateNo(pdNo, cateNo, thisCateNo);
        return ResponseEntity.ok(livingroomItem);
    }

    public ResponseEntity<StorageItem> getAllStorageItemsByPK(int pdNo, String cateNo, String thisCateNo) {
        StorageItem storageItem = storageItemRepository.findItemByPdNoAndCateNoAndThisCateNo(pdNo, cateNo, thisCateNo);
        return ResponseEntity.ok(storageItem);
    }


    //카테고리별 검색
    public List<Item> searchCateItem(String category, String thisCate, String searchKeyword){
        switch (category) {
            case "침실가구":
                return bedroomItemRepository.searchItemByCate(thisCate, searchKeyword);
            case "주방가구":
                return kitchenItemRepository.searchItemByCate(thisCate, searchKeyword);
            case "서재/사무용가구":
                return libraryItemRepository.searchItemByCate(thisCate, searchKeyword);
            case "거실가구":
                return livingroomItemRepository.searchItemByCate(thisCate, searchKeyword);
            case "수납가구":
                return storageItemRepository.searchItemByCate(thisCate, searchKeyword);
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
