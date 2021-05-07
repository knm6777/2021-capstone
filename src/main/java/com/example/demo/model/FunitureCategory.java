package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "FurnitureCategory")
@DynamicInsert
@DynamicUpdate

public class FunitureCategory {
    @Id
    @Column(name = "category_no")
    private String cateNo;
}
