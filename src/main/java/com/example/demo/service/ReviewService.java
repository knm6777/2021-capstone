package com.example.demo.service;

import com.example.demo.model.review.*;
import com.example.demo.repository.review.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private BedroomReviewRepository bedroomReviewRepository;
    @Autowired
    private KitchenReviewRepository kitchenReviewRepository;
    @Autowired
    private LibraryReviewRepository libraryReviewRepository;
    @Autowired
    private LivingroomReviewRepository livingroomReviewRepository;
    @Autowired
    private StorageReviewRepository storageReviewRepository;

    //리뷰 객체 리스트로 가져오기
    public List<BedroomReview> getAllBedroomReviews(int pd_no, String bedCateNo, String categoryNo){
        return bedroomReviewRepository.findReviewByPdNoAndSubcateNoAndCategoryNo(pd_no, bedCateNo, categoryNo);
    }
    public List<KitchenReview> getAllKitchenReviews(int pd_no, String kitchenCateNo, String categoryNo){
        return kitchenReviewRepository.findReviewByPdNoAndSubcateNoAndCategoryNo(pd_no, kitchenCateNo, categoryNo);
    }
    public List<LibraryReview> getAllLibraryReviews(int pd_no, String librarycateNo, String categoryNo){
        return libraryReviewRepository.findReviewByPdNoAndSubcateNoAndCategoryNo(pd_no, librarycateNo, categoryNo);
    }
    public List<LivingroomReview> getAllLivingroomReviews(int pd_no, String livingCateNo, String categoryNo){
        return livingroomReviewRepository.findReviewByPdNoAndSubcateNoAndCategoryNo(pd_no, livingCateNo, categoryNo);
    }
    public List<StorageReview> getAllStorageReviews(int pd_no, String storageCateNo, String categoryNo){
        return storageReviewRepository.findReviewByPdNoAndSubcateNoAndCategoryNo(pd_no, storageCateNo, categoryNo);
    }


}
