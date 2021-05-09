package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Like;
import com.example.demo.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    // 회원 id 별 좋아요 목록 가져오기
    // cRud
    public List<Like> getAllCartListByUserId(String userId) {
        return likeRepository.findAllByUserId(userId);
    }
//    public ResponseEntity<List<Like>> getAllCartListById(String id) {
//        List<Like> likeList = likeRepository.findAllByUserId(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Not exist LikeList by id : ["+id+"]"));
//
//        return ResponseEntity.ok(likeList);
//    }

    // 좋아요 리스트에 아이템 추가
    // Crud
    public Like createLike(Like like) {
        return likeRepository.save(like);
    }

    // 좋아요 리스트에 아이템 해제
    // cruD
    public void deleteLikeByLikeNo(String likeNo) {
        likeRepository.delete(likeRepository.findByLikeNo(likeNo));
    }



}
