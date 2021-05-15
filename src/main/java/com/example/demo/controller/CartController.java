package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.user.Cart;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartService cartService;

    // 회원 id 별 장바구니 목록 가져오기
    @PreAuthorize("permitAll()")
    @GetMapping("/cart")
    public ResponseEntity<List<Cart>> getAllCartListById(@RequestParam(value="user_id") String id) {

        List<Cart> cartList = cartService.getAllCartListByUserId(id);

        return ResponseEntity.ok().body(cartList);
    }

    // 아이템 장바구니 목록에 저장
    // 이미 있는 아이템 일 시 수량 증가
    // json data 에 유저 아이디 넣음
    @PreAuthorize("permitAll()")
    @PostMapping("/cart")
    public ResponseEntity<Void> createLike(@RequestBody Cart cart, UriComponentsBuilder ucBuilder){

        Cart savedCart = cartService.isCartExist(cart);
        // 이미 있는 아이템 일 시 장바구니에 넣으려했던 수량만큼 증가
        if (savedCart != null) {
            cartService.updateCartVolume(savedCart.getCartNo(), cart.getVolume() + savedCart.getVolume());
        } else{
            cartService.createCart(cart);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/cart").build().toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    // 장바구니 목록 내에 아이템 삭제
    @DeleteMapping("/cart/{cartNo}")
    @Transactional
    @PreAuthorize("permitAll()")
    public ResponseEntity<Cart> deleteLike(@PathVariable Long cartNo) {

        Cart cart = cartService.findByCartNo(cartNo);
        if (cart == null) {
            throw new ResourceNotFoundException("This item does not exist in the cart.");
        }

        cartService.deleteCartByCartNo(cartNo);

        return new ResponseEntity<Cart>(HttpStatus.NO_CONTENT);
    }

    // 회원 id 별 장바구니 목록 전체 삭제
    @PreAuthorize("permitAll()")
    @Transactional
    @DeleteMapping("/cart/all/{userId}")
    public ResponseEntity<List<Cart>> deleteAllLikeByUserId(@PathVariable String userId) {

        List<Cart> cartList = cartService.getAllCartListByUserId(userId);
        if (cartList.isEmpty()){
            throw new ResourceNotFoundException("This cart is already empty.");
        }

        cartService.deleteAllCartByUserId(userId);

        return new ResponseEntity<List<Cart>>(HttpStatus.NO_CONTENT);
    }

    // update
    // 수량 업데이트 vol 로 변경
    @PutMapping("/cart/{cartNo}/{volume}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Cart> updateItemVolume(@PathVariable Long cartNo, @PathVariable int volume){

        Cart cart = cartService.findByCartNo(cartNo);
        if (cart == null) {
            throw new ResourceNotFoundException("This item does not exist in the cart.");
        }

        if (volume <= 0)
            throw new ResourceNotFoundException("Only integers greater than or equal to 0 can be entered.");

        Cart updatedCart = cartService.updateCartVolume(cartNo, volume);

        return new ResponseEntity<Cart>(updatedCart, HttpStatus.OK);
    }
}
