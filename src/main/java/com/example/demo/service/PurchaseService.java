package com.example.demo.service;

import com.example.demo.model.Purchase;
import com.example.demo.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    // 구매 목록 저장하기
    // Crud
    public void createPurchase(Purchase purchase){
        purchaseRepository.save(purchase);
    }

    // 구매 목록 user id 별 전체 보여주기
    // cRud -1
    public List<Purchase> getAllPurchaseListByUserId(String userId) {
        return purchaseRepository.findAllByUserId(userId);
    }

    // 구매 목록 날짜 별 전체 보여주기
    // cRud -2
    public List<Purchase> getAllPurchaseListByPurchaseDate(LocalDateTime purDate) {
        return purchaseRepository.findAllByPurchaseDate(purDate);
    }

    // 구매 목록 purNo(pk) 별 하나씩 찾기
    // cRud -3
    public Purchase getPurchaseByPurchaseNo(Long purchaseNo) {
        return purchaseRepository.findByPurchaseNo(purchaseNo);
    }

    // 구매 목록 날짜 별 전체 삭제
    // 내역 관련
    // cruD -1
    public void deletePurchaseByPurchaseDate(LocalDateTime purDate) {
        purchaseRepository.deleteAllByPurchaseDate(purDate);
    }

    // 구매 목록 아이템 별 삭제
    // 내역 관련
    // cruD -2
    public void deletePurchaseByPurchaseNo(Long purNo) {
        purchaseRepository.delete(purchaseRepository.findByPurchaseNo(purNo));
    }

    // 구매 목록 user_id 별 전체 삭제
    // 내역 관련
    // cruD -3
    public  void deletePurchaseByUserId(String userId) {
        purchaseRepository.deleteAllByUserId(userId);
    }

}
