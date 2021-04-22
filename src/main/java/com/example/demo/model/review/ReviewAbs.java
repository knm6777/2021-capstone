package com.example.demo.model.review;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@IdClass(ReviewPK.class)
public abstract class ReviewAbs {

    @Id
    @Column(name = "review_no")
    private int reviewNo;

    @Id
    @Column(name = "pd_no")
    private int pdNo;

    @Id
    private String subcateNo;

    @Id
    @Column(name="category_no")
    private String categoryNo;

    private int star;

    private String review;

    private String customerId;

    private String reviewDate;



}
