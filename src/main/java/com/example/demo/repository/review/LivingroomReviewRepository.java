package com.example.demo.repository.review;

import com.example.demo.model.review.LivingroomReview;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivingroomReviewRepository extends ReviewRepository<LivingroomReview, Integer> {

    public final static String SELECT_REVIEW_LIST_PAGED = ""
            + "SELECT "
            + "review_no,"
            + "pd_no,"
            + "livingcate_no,"
            + "category_no,"
            + "star,"
            + "review,"
            + "customerId,"
            + "reviewDate"
            + " FROM LivingroomReview WHERE 0 < review_no "
            + "AND pd_no = ?1 "
            + "ORDER BY review_no DESC LIMIT ?2, ?3";

    @Query(value = SELECT_REVIEW_LIST_PAGED, nativeQuery = true)
    public List<LivingroomReview> findFromTo(
            final Integer pdNo,
            final Integer objectStartNum,
            final Integer objectEndNum);

}
