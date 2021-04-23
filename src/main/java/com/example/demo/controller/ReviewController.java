package com.example.demo.controller;

import com.example.demo.model.review.Review;
import com.example.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    //리뷰 가져오기
    @PreAuthorize("permitAll()")
    @GetMapping("/reviews")
    public ResponseEntity<?> getReviewsByItems( @RequestParam(value = "category", required = false) String category,
                                               @RequestParam(value = "subcate", required = false) String subcate,
                                               @RequestParam(value = "pdNo", required = false) int pdNo){

        final List<?> reviewlist;

        switch (category) {
            case "침실가구":
                reviewlist = reviewService.getAllBedroomReviews(pdNo, subcate, category);
                return new ResponseEntity<>(reviewlist, HttpStatus.OK);
            case "주방가구":
                reviewlist = reviewService.getAllKitchenReviews(pdNo, subcate, category);
                return new ResponseEntity<>(reviewlist, HttpStatus.OK);
            case "서재/사무용가구":
                reviewlist = reviewService.getAllLibraryReviews(pdNo, subcate, category);
                return new ResponseEntity<>(reviewlist, HttpStatus.OK);
            case "거실가구":
                reviewlist = reviewService.getAllLivingroomReviews(pdNo, subcate, category);
                return new ResponseEntity<>(reviewlist, HttpStatus.OK);
            case "수납가구":
                reviewlist = reviewService.getAllStorageReviews(pdNo, subcate, category);
                return new ResponseEntity<>(reviewlist, HttpStatus.OK);
            default:
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
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
