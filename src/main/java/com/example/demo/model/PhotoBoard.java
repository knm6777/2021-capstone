package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name="PhotoBoard")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class PhotoBoard implements Serializable {

    //번호
    @Id
    @Column(name="photoboard_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pboardNo;

    //제목
    @Column(name = "photoboard_title")
    private String pboardTitle;

    //작성자
    @Column(name = "photoboard_writer")
    private String pboardWriter;

    //등록일
    @Column(name = "photoboard_insertTime", columnDefinition = "datetime default now()")
    private LocalDateTime pboardInsertTime;

    //조회수
    @Column(name = "photoboard_views", columnDefinition = "integer default 0")
    private int pboardViews;

    //내용
    @Column(name = "photoboard_content")
    private String pboardContent;

    //수정일
    @Column(name = "photoboard_updateTime")
    private LocalDateTime pboardUpdateTime;

    //파일
    @Column(name = "photoboard_fileUrl")
    private String pboardFileUrl;

    @OneToMany(mappedBy="pboardNo", cascade=CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=true)
    @Transient
    public List<PhotoComment> photoComments = new ArrayList<>();


}
