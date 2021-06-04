package com.example.demo.model.review;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@IdClass(ReviewPK.class)
public abstract class ReviewAbs {

    //리뷰번호
    @Id
    @Column(name = "review_no")
    private int reviewNo;

    //상품번호
    @Id
    @Column(name = "pd_no")
    private int pdNo;

    //중간카테
    @Id
    private String subcateNo;

    //큰카테
    @Id
    @Column(name="category_no")
    private String categoryNo;

    //별점
    private int star;

    //리뷰내용
    private String review;

    //고객아이디
    private String customerId;

    //등록날짜 
    // db에 들어가있는 형식이 날짜타입이랑 안맞아서 string으로 함
    private String reviewDate;

    @Transient
    private String predict = null;


}
