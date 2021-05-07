package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "LikeList")
public class LikeList {
    // PK
    @Id
    @Column(name="like_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int likeNo;

    // User pk
    @Column(name = "user_id")
    private String userId;

    // Item 조인 테이블?? 만들기
    // Item pk 1: 카테고리 별 제품 id
    @Column(name = "pd_no")
    private Integer pdNo;

    // Item pk 2: 서브 카테고리 id
    @Column(name = "subcate_no")
    private String subcateNo;

    // Item pk 3: 큰 카테고리 id
    @Column(name = "category_no")
    private String categoryNo;
}
