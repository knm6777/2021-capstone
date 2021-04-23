package com.example.demo.service;

import com.example.demo.model.PhotoBoard;
import com.example.demo.model.review.*;
import com.example.demo.repository.review.*;
import com.example.demo.util.PagingUtil;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.apache.commons.io.IOUtils;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.validation.constraints.NotNull;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    public ResponseEntity<Map> getReviewsPaging(Integer p_num, int pd_no, String subCateNo, String categoryNo) throws IOException {
        Map result = null;

        PagingUtil pu = new PagingUtil(p_num, 10, 10); // ($1:표시할 현재 페이지, $2:한페이지에 표시할 글 수, $3:한 페이지에 표시할 페이지 버튼의 수 )

        System.out.println("p_num : "+p_num);
        System.out.println(pu.toString());

        List<?> reviewlist;
        int allCount;

        switch (categoryNo) {
            case "침실가구":
                List<BedroomReview> list = bedroomReviewRepository.findFromTo(pd_no, pu.getObjectStartNum(), pu.getObjectCountPerPage());

                allCount = bedroomReviewRepository.countByPdNoAndSubcateNoAndCategoryNo(pd_no, subCateNo, categoryNo);
                pu.setObjectCountTotal(allCount);
                pu.setCalcForPaging();

                if (list == null || list.size() == 0) {
                    return null;
                }

                result = new HashMap<>();
                result.put("pagingData", pu);
                result.put("list", list);
                break;

            case "주방가구":
                List<KitchenReview> kitchenReviewList = kitchenReviewRepository.findFromTo(pd_no, pu.getObjectStartNum(), pu.getObjectCountPerPage());

                allCount = kitchenReviewRepository.countByPdNoAndSubcateNoAndCategoryNo(pd_no, subCateNo, categoryNo);
                pu.setObjectCountTotal(allCount);
                pu.setCalcForPaging();

                if (kitchenReviewList == null || kitchenReviewList.size() == 0) {
                    return null;
                }

                result = new HashMap<>();
                result.put("pagingData", pu);
                result.put("list", kitchenReviewList);
                break;

            case "서재/사무용가구":
                List<LibraryReview> libraryReviewList = libraryReviewRepository.findFromTo(pd_no, pu.getObjectStartNum(), pu.getObjectCountPerPage());

                allCount = libraryReviewRepository.countByPdNoAndSubcateNoAndCategoryNo(pd_no, subCateNo, categoryNo);
                pu.setObjectCountTotal(allCount);
                pu.setCalcForPaging();

                if (libraryReviewList == null || libraryReviewList.size() == 0) {
                    return null;
                }

                result = new HashMap<>();
                result.put("pagingData", pu);
                result.put("list", libraryReviewList);
                break;

            case "거실가구":
                List<LivingroomReview> livingroomReviewList = livingroomReviewRepository.findFromTo(pd_no, pu.getObjectStartNum(), pu.getObjectCountPerPage());

                allCount = livingroomReviewRepository.countByPdNoAndSubcateNoAndCategoryNo(pd_no, subCateNo, categoryNo);
                pu.setObjectCountTotal(allCount);
                pu.setCalcForPaging();

                if (livingroomReviewList == null || livingroomReviewList.size() == 0) {
                    return null;
                }

                result = new HashMap<>();
                result.put("pagingData", pu);
                result.put("list", livingroomReviewList);
                break;

            case "수납가구":
                List<StorageReview> storageReviewList = storageReviewRepository.findFromTo(pd_no, pu.getObjectStartNum(), pu.getObjectCountPerPage());

                allCount = storageReviewRepository.countByPdNoAndSubcateNoAndCategoryNo(pd_no, subCateNo, categoryNo);
                pu.setObjectCountTotal(allCount);
                pu.setCalcForPaging();

                if (storageReviewList == null || storageReviewList.size() == 0) {
                    return null;
                }

                result = new HashMap<>();
                result.put("pagingData", pu);
                result.put("list", storageReviewList);
                break;

        }


        return ResponseEntity.ok(result);
    }


    //리뷰 작성
    public ResponseEntity<?> createReview(Review review, int pdNo, String cateNo, String categoryNo){
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy.MM.dd"));
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
