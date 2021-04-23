package com.example.demo.service;

import com.example.demo.model.review.*;
import com.example.demo.repository.review.*;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    //리뷰 작성
    public ResponseEntity<?> createReview(Review review, int pdNo, String cateNo, String categoryNo){
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        switch (categoryNo){

            case "침실가구":
                BedroomReview bedroomReview = new BedroomReview();
                bedroomReview.setReviewNo(bedroomReviewRepository.countByPdNoAndSubcateNoAndCategoryNo(pdNo, cateNo, categoryNo));
                bedroomReview.setPdNo(pdNo);
                bedroomReview.setSubcateNo(cateNo);
                bedroomReview.setCategoryNo(categoryNo);
                bedroomReview.setStar(review.getStar());
                bedroomReview.setCustomerId(review.getCustomerId());
                bedroomReview.setReview(review.getReview());

                bedroomReview.setReviewDate(date);
                bedroomReviewRepository.save(bedroomReview);
                return new ResponseEntity<>(bedroomReview, HttpStatus.CREATED);
            case "주방가구":
                KitchenReview kitchenReview = new KitchenReview();
                kitchenReview.setReviewNo(kitchenReviewRepository.countByPdNoAndSubcateNoAndCategoryNo(pdNo, cateNo, categoryNo));
                kitchenReview.setPdNo(pdNo);
                kitchenReview.setSubcateNo(cateNo);
                kitchenReview.setCategoryNo(categoryNo);
                kitchenReview.setStar(review.getStar());
                kitchenReview.setCustomerId(review.getCustomerId());
                kitchenReview.setReview(review.getReview());
                kitchenReview.setReviewDate(date);
                kitchenReviewRepository.save(kitchenReview);
                return new ResponseEntity<>(kitchenReview, HttpStatus.CREATED);
            case "서재/사무용가구":
                LibraryReview libraryReview = new LibraryReview();
                libraryReview.setReviewNo(kitchenReviewRepository.countByPdNoAndSubcateNoAndCategoryNo(pdNo, cateNo, categoryNo));
                libraryReview.setPdNo(pdNo);
                libraryReview.setSubcateNo(cateNo);
                libraryReview.setCategoryNo(categoryNo);
                libraryReview.setStar(review.getStar());
                libraryReview.setCustomerId(review.getCustomerId());
                libraryReview.setReview(review.getReview());
                libraryReview.setReviewDate(date);
                libraryReviewRepository.save(libraryReview);
                return new ResponseEntity<>(libraryReview, HttpStatus.CREATED);
            case "거실가구":
                LivingroomReview livingroomReview = new LivingroomReview();
                livingroomReview.setReviewNo(livingroomReviewRepository.countByPdNoAndSubcateNoAndCategoryNo(pdNo, cateNo, categoryNo));
                livingroomReview.setPdNo(pdNo);
                livingroomReview.setSubcateNo(cateNo);
                livingroomReview.setCategoryNo(categoryNo);
                livingroomReview.setStar(review.getStar());
                livingroomReview.setCustomerId(review.getCustomerId());
                livingroomReview.setReview(review.getReview());
                livingroomReview.setReviewDate(date);
                livingroomReviewRepository.save(livingroomReview);
                return new ResponseEntity<>(livingroomReview, HttpStatus.CREATED);
            case "수납가구":
                StorageReview storageReview = new StorageReview();
                storageReview.setReviewNo(storageReviewRepository.countByPdNoAndSubcateNoAndCategoryNo(pdNo, cateNo, categoryNo));
                storageReview.setPdNo(pdNo);
                storageReview.setSubcateNo(cateNo);
                storageReview.setCategoryNo(categoryNo);
                storageReview.setStar(review.getStar());
                storageReview.setCustomerId(review.getCustomerId());
                storageReview.setReview(review.getReview());
                storageReview.setReviewDate(date);
                storageReviewRepository.save(storageReview);
                return new ResponseEntity<>(storageReview, HttpStatus.CREATED);
            default:
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }





    }




}
