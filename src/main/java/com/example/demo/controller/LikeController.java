package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.user.Like;
import com.example.demo.service.LikeService;
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
public class LikeController {
    @Autowired
    private LikeService likeService;

    // 회원 id 별 좋아요 목록 가져오기
    @PreAuthorize("permitAll()")
    @GetMapping("/like")
    public ResponseEntity<List<Like>> getAllLikeListById(@RequestParam(value="user_id") String id) {

        List<Like> likeList = likeService.getAllLikeListByUserId(id);

        //return new ResponseEntity<List<Like>>(likeList, HttpStatus.OK);
        return ResponseEntity.ok().body(likeList);
    }

    // 아이템 좋아요 목록에 저장
    // json data 에 유저 아이디 넣음
    @PreAuthorize("permitAll()")
    @PostMapping("/like")
    public ResponseEntity<Void> createLike(@RequestBody Like like, UriComponentsBuilder ucBuilder){

        if (likeService.isLikeExist(like)) {
            throw new ResourceNotFoundException("This data already exists in the likes list.");
        }
        likeService.createLike(like);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/like").build().toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    // 좋아요 목록 내에 아이템 삭제
    @DeleteMapping("/like/{likeNo}")
    @Transactional
    @PreAuthorize("permitAll()")
    public ResponseEntity<Like> deleteLike(@PathVariable Long likeNo) {

        Like like = likeService.findByLikeNo(likeNo);
        if (like == null) {
            throw new ResourceNotFoundException("This item does not exist in the likes list.");
        }

        likeService.deleteLikeByLikeNo(likeNo);

        return new ResponseEntity<Like>(HttpStatus.NO_CONTENT);
    }

    // 회원 id 별 좋아요 목록 전체 삭제
    @PreAuthorize("permitAll()")
    @Transactional
    @DeleteMapping("/like/all/{userId}")
    public ResponseEntity<List<Like>> deleteAllLikeByUserId(@PathVariable String userId) {

        List<Like> likeList = likeService.getAllLikeListByUserId(userId);
        if (likeList.isEmpty()){
            throw new ResourceNotFoundException("This list is already empty.");
        }

        likeService.deleteAllLikeByUserId(userId);

        return new ResponseEntity<List<Like>>(HttpStatus.NO_CONTENT);
    }
}
