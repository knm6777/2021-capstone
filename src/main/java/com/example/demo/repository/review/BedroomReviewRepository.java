package com.example.demo.repository.review;

import com.example.demo.model.review.BedroomReview;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BedroomReviewRepository extends ReviewRepository<BedroomReview, Integer>{

    public final static String SELECT_REVIEW_LIST_PAGED = ""
            + "SELECT "
            + "review_no,"
            + "pd_no,"
            + "bedcate_no,"
            + "category_no,"
            + "star,"
            + "review,"
            + "customerId,"
            + "reviewDate"
            + " FROM BedroomReview WHERE 0 < review_no "
            + "AND pd_no = ?1 "
            + "ORDER BY review_no DESC LIMIT ?2, ?3";


    @Query(value = SELECT_REVIEW_LIST_PAGED, nativeQuery = true)
    public List<BedroomReview> findFromTo(
            final Integer pdNo,
            final Integer objectStartNum,
            final Integer objectEndNum
            );


}
