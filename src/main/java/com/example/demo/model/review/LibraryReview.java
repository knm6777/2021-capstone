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
@Table(name = "LibraryReview")
@DynamicInsert
@DynamicUpdate
@AttributeOverride(name="subcateNo", column=@Column(name="librarycate_no"))
public class LibraryReview extends ReviewAbs {
}
