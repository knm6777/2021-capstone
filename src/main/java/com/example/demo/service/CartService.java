package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.user.Cart;
import com.example.demo.repository.user.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    // 장바구니에 넣은 한 아이템 찾기
    public Cart findByCartNo(Long CartNo) {
        return cartRepository.findByCartNo(CartNo);
    }

    // 회원 id 별 장바구니 목록 가져오기
    // cRud
    public List<Cart> getAllCartListByUserId(String userId) {
        return cartRepository.findAllByUserId(userId);
    }

    // 장바구니 리스트에 아이템 추가
    // Crud
    public void createCart(Cart cart) {
        cartRepository.save(cart);
    }

    // 장바구니 리스트에 아이템 삭제
    // cruD -1
    public void deleteCartByCartNo(Long cartNo) {
        cartRepository.delete(cartRepository.findByCartNo(cartNo));
    }

    // userId인 유저의 장바구니 목록 전체 삭제
    // cruD -2
    public void deleteAllCartByUserId(String userId){
        cartRepository.deleteAllByUserId(userId);
    }

    // 장바구니 아이템 수량 업데이트
    // vol 로 변경
    // crUd
    public Cart updateCartVolume(Long cartNo, int vol) {
        Cart cart = cartRepository.findById(cartNo)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Cart Data by idx : ["+cartNo+"]"));

        cart.setVolume(vol);

        return cartRepository.save(cart);
    }

    // 장바구니 목록에 이미 존재하는지 확인
    // 없을 시, return null
    public Cart isCartExist(Cart cart) {
        return cartRepository.findByUserIdAndPdNoAndSubcateNoAndCategoryNo(cart.getUserId(), cart.getPdNo(), cart.getSubcateNo(), cart.getCategoryNo());
    }
}
