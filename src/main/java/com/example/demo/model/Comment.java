package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_comment")
@DynamicInsert
@DynamicUpdate
public class Comment {

    /*번호*/
    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    /*글 번호*/
    @Column(name = "board_idx")
    private int boardIdx;

    /* 내용 */
    @Column(name = "content")
    private String content;

    /*작성자*/
    @Column(name = "writer")
    private String writer;

    /*삭제 여부 -> 안쓸것같음;;ㅎㅎ*/
    @Column(name = "delete_yn")
    private String deleteYn;

    /*등록일*/
    @Column(name = "insert_time")
    private LocalDateTime insertTime;

    /*수정일*/
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    /*삭제일*/
    @Column(name = "delete_time")
    private LocalDateTime deleteTime;
}
