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
@Table(name = "StorageReview")
@DynamicInsert
@DynamicUpdate
@AttributeOverride(name="subcateNo", column=@Column(name="storagecate_no"))
public class StorageReview extends ReviewAbs {
}
