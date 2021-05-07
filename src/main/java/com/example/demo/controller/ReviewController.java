package com.example.demo.controller;

import com.example.demo.model.review.Review;
import com.example.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;



    //리뷰 페이징
    @PreAuthorize("permitAll()")
    @GetMapping("/reviews")
    public ResponseEntity<?> getReviewPaging(@RequestParam(value="p_num", required = false) Integer p_num,
                                               @RequestParam(value = "category", required = false) String category,
                                               @RequestParam(value = "subcate", required = false) String subcate,
                                               @RequestParam(value = "pdNo", required = false) int pdNo) throws IOException {

        if (p_num == null || p_num <= 0) p_num = 1;
        return reviewService.getReviewsPaging(p_num, pdNo, subcate, category);
    }

    //리뷰 작성
    @PreAuthorize("permitAll()")
    @PostMapping("/reviews")
    public ResponseEntity<?> createReviewsByItem(@RequestParam(value = "category", required = false) String category,
                                              @RequestParam(value = "subcate", required = false) String subcate,
                                              @RequestParam(value = "pdNo", required = false) int pdNo,
                                              @RequestBody Review review){

        return reviewService.createReview(review, pdNo, subcate, category);

    }

}
