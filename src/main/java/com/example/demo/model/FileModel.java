package com.example.demo.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tb_file")
@DynamicInsert
@DynamicUpdate
public class FileModel {
    /** 번호 (PK) */
    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @Column(name = "fileUrl")
    private String fileUrl;

    /*글 번호*/
    @Column(name = "board_idx")
    private int boardIdx;
}
