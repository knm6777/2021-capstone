package com.example.demo.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
@IdClass(ItemPK.class)
public abstract class ItemAbs implements Serializable {
    // 카테고리 별 제품번호
    @Id
    @Column(name="pd_no")
    private int pdNo;

    // 큰 카테고리 이름
    @Id
    @Column(name = "category_no")
    private String cateNo;
    // 작은 카테고리 이름
    @Id
    @Column(name = "subcate_no")
    private String subcateNo;

    // <-- 기본 키 -->


    // 제품 명
    @Column(name="pd_title")
    private String pdTitle;

    // 제품 링크
    @Column(name="pd_href")
    private String pdHref;

    // 제품 이미지
    @Column(name="pd_img")
    private String pdImg;

    // 제품 가격
    @Column(name="pd_price")
    private String pdPrice;

    // 제품 판매처 이름
    @Column(name="pd_mall")
    private String pdMall;


}
