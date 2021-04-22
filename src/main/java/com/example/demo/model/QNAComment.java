package com.example.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

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
@Table(name = "QNAComment")
@DynamicInsert
@DynamicUpdate
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class QNAComment implements Serializable {

    /*번호*/
    @Id
    @Column(name = "qcomment_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int qcommentNo;

    /* 내용 */
    @Column(name = "qcomment_content")
    private String qcommentContent;

    /*작성자*/
    @Column(name = "qcomment_writer")
    private String qcommentWriter;

    /*등록일*/
    @Column(name = "qcomment_insertTime", columnDefinition = "datetime default now()")
    private LocalDateTime qcommentInsertTime;

    /*수정일*/
    @Column(name = "qcomment_updateTime")
    private LocalDateTime qcommentUpdateTime;

    //댓글 단 글
    @ManyToOne
    @JoinColumn(name = "qboard_no")
    private QNABoard qboardNo;

}
