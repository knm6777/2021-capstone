package com.example.demo.repository.review;

import com.example.demo.model.review.KitchenReview;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KitchenReviewRepository extends ReviewRepository<KitchenReview, Integer>{

    List<KitchenReview> findAllBySubcateNoAndPdNo(String subcateNo, int pdNo, Pageable paging);

    public final static String SELECT_REVIEW_LIST_PAGED = ""
            + "SELECT "
            + "review_no,"
            + "pd_no,"
            + "subcate_no,"
            + "category_no,"
            + "star,"
            + "review,"
            + "customerId,"
            + "reviewDate"
            + " FROM KitchenReview WHERE 0 < review_no "
            + "AND pd_no = ?2 "
            + "AND subcate_no = ?1 "
            + "ORDER BY review_no DESC LIMIT ?3, ?4";

    @Query(value = SELECT_REVIEW_LIST_PAGED, nativeQuery = true)
    public List<KitchenReview> findFromTo(
            final String subcateNo,
            final Integer pdNo,
            final Integer objectStartNum,
            final Integer objectEndNum);

}
