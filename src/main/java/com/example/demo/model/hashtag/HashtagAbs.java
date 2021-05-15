package com.example.demo.model.hashtag;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
@IdClass(HashtagPK.class)
public abstract class HashtagAbs {

    @Id
    private int id;

    private String name;

    private int frequency;

    @Column(name = "pd_no")
    private int pdNo;

    @Column(name = "subcate_no")
    private String subcateNo;


    @Column(name="category_no")
    private String categoryNo;


}
