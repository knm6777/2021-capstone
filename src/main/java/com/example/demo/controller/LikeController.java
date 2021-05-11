package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Like;
import com.example.demo.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/like")
public class LikeController {
    @Autowired
    private LikeService likeService;

    // 회원 id 별 좋아요 목록 가져오기
    @PreAuthorize("permitAll()")
    @GetMapping("/")
    public ResponseEntity<List<Like>> getAllLikeListById(@RequestParam(value="user_id") String id) {
        List<Like> likeList = likeService.getAllLikeListByUserId(id);

        //return new ResponseEntity<List<Like>>(likeList, HttpStatus.OK);
        return ResponseEntity.ok().body(likeList);
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/")
    public ResponseEntity<Like> createLike(@RequestBody Like like, ){
        likeService.createLike(like);
        //URI location =

        //return ResponseEntity.created(location).build();
        return ResponseEntity.created(linkTo(LikeController.class).slash(id).toUri()).build();

    }

}
