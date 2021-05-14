package com.example.demo.service;

import com.example.demo.model.Like;
import com.example.demo.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    // 좋아요 한 아이템 찾기
    public Like findByLikeNo(Long likeNo) {
        return likeRepository.findByLikeNo(likeNo);
    }

    // 회원 id 별 좋아요 목록 가져오기
    // cRud
    public List<Like> getAllLikeListByUserId(String userId) {
        return likeRepository.findAllByUserId(userId);
    }

    // 좋아요 리스트에 아이템 추가
    // Crud
    public void createLike(Like like) {
        likeRepository.save(like);
    }

    // 좋아요 리스트에 아이템 삭제
    // cruD -1
    public void deleteLikeByLikeNo(Long likeNo) {
        likeRepository.delete(likeRepository.findByLikeNo(likeNo));
    }

    // userId인 유저의 좋아요 목록 전체 삭제
    // cruD -2
    public void deleteAllLikeByUserId(String userId){
        likeRepository.deleteAllByUserId(userId);
    }

    // 좋아요 목록에 이미 존재하는지 확인
    public boolean isLikeExist(Like like) {
        return likeRepository.findByUserIdAndPdNoAndSubcateNoAndCategoryNo(like.getUserId(), like.getPdNo(), like.getSubcateNo(), like.getCategoryNo()) != null;
    }
}
