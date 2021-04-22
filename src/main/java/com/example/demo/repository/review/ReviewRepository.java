package com.example.demo.repository.review;

import com.example.demo.model.review.ReviewAbs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface ReviewRepository<T extends ReviewAbs, ID extends Serializable> extends JpaRepository<T, ID> {

    List<T> findReviewByPdNoAndSubcateNoAndCategoryNo(int pd_no, String subcateNo, String categoryNo);

}
