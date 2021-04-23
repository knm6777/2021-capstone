package com.example.demo.model.review;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "BedroomReview")
//@Table(name = "testReview") //테스트시 사용하는 테이블
@DynamicInsert
@DynamicUpdate
@AttributeOverride(name="subcateNo", column=@Column(name="bedcate_no"))
public class BedroomReview extends ReviewAbs {

}
