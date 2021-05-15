package com.example.demo.repository.user;

import com.example.demo.model.user.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {

    public List<Like> findAllByUserId(String id);
    public Like findByLikeNo(Long likeNo);
    public Like findByUserIdAndPdNoAndSubcateNoAndCategoryNo(String userId, Integer pdNo, String subcateNo, String categoryNo);
    public void deleteAllByUserId(String userId);
}
