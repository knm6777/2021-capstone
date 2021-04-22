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
@IdClass(CategoryPK.class)
public abstract class Category implements Serializable {
    @Id
    @Column(name = "category_no")
    public String cateNo;

    @Id
    public String thisCateNo;
}
