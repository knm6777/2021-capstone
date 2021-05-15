package com.example.demo.model.board;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "QNABoard")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@DynamicInsert
@DynamicUpdate
public class QNABoard {

    /** 번호 */
    @Id
    @Column(name = "qboard_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int qboardNo;

    /** 제목 */
    @Column(name = "qboard_title")
    private String qboardTitle;

    /** 내용 */
    @Column(name = "qboard_content")
    private String qboardContent;

    /** 작성자 */
    @Column(name = "qboard_writer")
    private String qboardWriter;

    /** 조회 수 */
    @Column(name = "qboard_views", columnDefinition = "integer default 0")
    private int qboardViews;

    /** 등록일 */
    @Column(name = "qboard_insertTime", columnDefinition = "datetime default now()")
    private LocalDateTime qboardInsertTime;

    /** 수정일 */
    @Column(name = "qboard_updateTime")
    private LocalDateTime qboardUpdateTime;

    /** 작성자 */
    @Column(name = "qboard_fileUrl")
    private String qboardFileUrl;

    @OneToMany(mappedBy="qboardNo", cascade= CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=true)
    @Transient
    public List<QNAComment> qboardComments = new ArrayList<>();



}