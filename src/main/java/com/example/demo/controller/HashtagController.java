package com.example.demo.controller;

import com.example.demo.model.hashtag.AdjectiveHashtag;
import com.example.demo.model.hashtag.NounHashtag;
import com.example.demo.service.HashtagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class HashtagController {

    @Autowired
    private HashtagService hashtagService;

    //상품정보로 모든 태그찾기
    @PreAuthorize("permitAll()")
    @GetMapping("/hashtag/noun")
    public ResponseEntity<List<NounHashtag>> getAllNounHashtagByProduct(@RequestParam(value="pdNo") int pdNo,
                                                                     @RequestParam(value="subcateNo") String subcateNo,
                                                                        @RequestParam(value="cateNo") String cateNo){
        return ResponseEntity.ok(hashtagService.getNounHashtagByProduct(pdNo, subcateNo, cateNo));
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/hashtag/adj")
    public ResponseEntity<List<AdjectiveHashtag>> getAllAdjHashtagByProduct(@RequestParam(value="pdNo") int pdNo,
                                                                            @RequestParam(value="subcateNo") String subcateNo,
                                                                            @RequestParam(value="cateNo") String cateNo){
        return ResponseEntity.ok(hashtagService.getAdjHashtagByProduct(pdNo, subcateNo, cateNo));
    }

    //top5
    @PreAuthorize("permitAll()")
    @GetMapping("/hashtag/topNoun")
    public ResponseEntity<List<NounHashtag>> getNounTagTop5(@RequestParam(value="pdNo") int pdNo,
                                                            @RequestParam(value="subcateNo") String subcateNo,
                                                            @RequestParam(value="cateNo") String cateNo){
        return ResponseEntity.ok(hashtagService.getNounTagTop15(pdNo, subcateNo, cateNo));
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/hashtag/topAdj")
    public ResponseEntity<List<AdjectiveHashtag>> getAdjTagTop5(@RequestParam(value="pdNo") int pdNo,
                                                                @RequestParam(value="subcateNo") String subcateNo,
                                                                @RequestParam(value="cateNo") String cateNo){
        return ResponseEntity.ok(hashtagService.getAdjTagTop5(pdNo, subcateNo, cateNo));
    }


    //해시태그로 상품찾기
    @PreAuthorize("permitAll()")
    @GetMapping("/hastag/nounP")
    public ResponseEntity<List<NounHashtag>> getProductByNounHash(@RequestParam(value = "tag") String tag){
        return ResponseEntity.ok(hashtagService.getProductByNounHashtag(tag));
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/hashtag/adjP")
    public ResponseEntity<List<AdjectiveHashtag>> getProductByAdjHash(@RequestParam(value = "tag") String tag){
        return ResponseEntity.ok(hashtagService.getProductByAdjHashtag(tag));
    }

}
