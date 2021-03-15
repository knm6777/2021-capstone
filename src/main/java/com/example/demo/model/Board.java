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
@Table(name = "tb_board_test")
@DynamicInsert
@DynamicUpdate
public class Board {

    /** 번호 (PK) */
    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    /** 제목 */
    @Column(name = "title")
    private String title;

    /** 내용 */
    @Column(name = "content")
    private String content;

    /** 작성자 */
    @Column(name = "writer")
    private String writer;

    /** 조회 수 */
    @Column(name = "view_cnt")
    private int viewCnt;

    /** 공지 여부 */
    @Column(name = "notice_yn")
    private Boolean noticeYn;

    /** 비밀 여부 */
    @Column(name = "secret_yn")
    private Boolean secretYn;

    /** 삭제 여부 */
    @Column(name = "delete_yn")
    private Boolean deleteYn;

    /** 등록일 */
    @Column(name = "insert_time")
    private LocalDateTime insertTime;

    /** 수정일 */
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    /** 삭제일 */
    @Column(name = "delete_time")
    private LocalDateTime deleteTime;


}