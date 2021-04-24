package com.example.demo.repository.review;

import com.example.demo.model.review.LibraryReview;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibraryReviewRepository extends ReviewRepository<LibraryReview, Integer>{

    public final static String SELECT_REVIEW_LIST_PAGED = ""
            + "SELECT "
            + "review_no,"
            + "pd_no,"
            + "librarycate_no,"
            + "category_no,"
            + "star,"
            + "review,"
            + "customerId,"
            + "reviewDate"
            + " FROM LibraryReview WHERE 0 < review_no "
            + "AND pd_no = ?2 "
            + "AND librarycate_no = ?1 "
            + "ORDER BY review_no DESC LIMIT ?3, ?4";

    @Query(value = SELECT_REVIEW_LIST_PAGED, nativeQuery = true)
    public List<LibraryReview> findFromTo(
            final String subcateNo,
            final Integer pdNo,
            final Integer objectStartNum,
            final Integer objectEndNum);

}
