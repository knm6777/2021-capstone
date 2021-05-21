package com.example.demo.model.user;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@DynamicInsert
@Table(name = "PurchaseList")
public class Purchase {
    // PK
    @Id
    @Column(name="purchase_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseNo;

    // User pk
    @Column(name = "user_id")
    private String userId;

    // 외래키 연결
    // Item pk 1: 카테고리 별 제품 id
    @Column(name = "pd_no")
    private Integer pdNo;

    // Item pk 2: 서브 카테고리 id
    @Column(name = "subcate_no")
    private String subcateNo;

    // Item pk 3: 큰 카테고리 id
    @Column(name = "category_no")
    private String categoryNo;

    // 수량
    @Column(name = "volume")
    private Integer volume;

    // 구매일(자동)
    @Column(name = "purchase_date", columnDefinition = "datetime default now()")
    private LocalDateTime purchaseDate;
}
