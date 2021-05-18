package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Purchase;
import com.example.demo.service.OrderService;
import com.example.demo.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private OrderService orderService;

    // 회원 id 별 구매 목록 가져오기
    @PreAuthorize("permitAll()")
    @GetMapping("/purchase/user_id")
    public ResponseEntity<List<Purchase>> getPurchaseListByUserId(@RequestParam(value="user_id") String id) {

        List<Purchase> purchaseList = purchaseService.getAllPurchaseListByUserId(id);

        return ResponseEntity.ok().body(purchaseList);
    }

    // 구매 날짜 별 구매 목록 가져오기
    @PreAuthorize("permitAll()")
    @GetMapping("/purchase/date")
    public ResponseEntity<List<Purchase>> getPurchaseListByDate(@RequestParam(value="date") LocalDateTime dateTime) {

        List<Purchase> purchaseList = purchaseService.getAllPurchaseListByPurchaseDate(dateTime);

        return ResponseEntity.ok().body(purchaseList);
    }

    // 구매(pk) 별 구매 목록 가져오기
    @PreAuthorize("permitAll()")
    @GetMapping("/purchase/p_no")
    public ResponseEntity<Purchase> getPurchaseByPurchaseId(@RequestParam(value="purchase_no") Long purNo) {

        Purchase purchase = purchaseService.getPurchaseByPurchaseNo(purNo);

        return ResponseEntity.ok().body(purchase);
    }

    // 아이템 구매 목록에 저장
    // 1. cart -> 구매, 2. 상품페이지 -> 구매
    // 카트와 json 형식 같음(date 자동 삽입)
    // order 목록에 함께 저장
    @PreAuthorize("permitAll()")
    @PostMapping("/purchase")
    public ResponseEntity<Void> createLike(@RequestBody Purchase purchase, UriComponentsBuilder ucBuilder){

        purchaseService.createPurchase(purchase);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/purchase").build().toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    // 구매 목록 내에 아이템 날짜 별 삭제
    @DeleteMapping("/purchase/{date}")
    @Transactional
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<Purchase>> deletePurchaseByDate(@PathVariable LocalDateTime date) {

        List<Purchase> purchases = purchaseService.getAllPurchaseListByPurchaseDate(date);
        if (purchases == null) {
            throw new ResourceNotFoundException("This purchase list is already empty.");
        }

        purchaseService.deletePurchaseByPurchaseDate(date);

        return new ResponseEntity<List<Purchase>>(HttpStatus.NO_CONTENT);
    }

    // 구매 목록 내에 아이템들 purchaseNo(pk) 로 찾아서 삭제
    @DeleteMapping("/purchase/{purNo}")
    @Transactional
    @PreAuthorize("permitAll()")
    public ResponseEntity<Purchase> deletePurchaseByPurchaseNo(@PathVariable Long purNo) {

        Purchase purchase = purchaseService.getPurchaseByPurchaseNo(purNo);

        if (purchase == null) {
            throw new ResourceNotFoundException("This item does not exist in the purchase list.");
        }

        purchaseService.deletePurchaseByPurchaseNo(purNo);

        return new ResponseEntity<Purchase>(HttpStatus.NO_CONTENT);
    }

    // 구매 목록 내에 아이템들 user id 별 삭제
    @DeleteMapping("/purchase/{userid}")
    @Transactional
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<Purchase>> deletePurchaseByDate(@PathVariable String userid) {

        List<Purchase> purchases = purchaseService.getAllPurchaseListByUserId(userid);
        if (purchases == null) {
            throw new ResourceNotFoundException("This purchase list is already empty.");
        }

        purchaseService.deletePurchaseByUserId(userid);

        return new ResponseEntity<List<Purchase>>(HttpStatus.NO_CONTENT);
    }

    // 구매 목록을 주문 목록에 저장 -> purchaseNo & UserId -> OrderList에 저장
    // Crud
}
