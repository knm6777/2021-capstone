package com.example.demo.model.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "OrderList")
public class Order {
    // PK
    @Id
    @Column(name="order_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OrderNo;

    // User pk
    @Column(name = "user_id")
    private String UserId;

    // Purchase pk
    @Column(name = "purchase_no")
    private Integer purchaseNo;
}
