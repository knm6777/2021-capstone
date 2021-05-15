package com.example.demo.model.board;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "PhotoComment")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@DynamicInsert
@DynamicUpdate
public class PhotoComment implements Serializable {

    //번호
    @Id
    @Column(name = "photocomment_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pcommentNo;


    //내용
    @Column(name = "photocomment_content")
    private String pcommentContent;

    //작성자
    @Column(name = "photocomment_writer")
    private String pcommentWriter;

    //등록일
    @Column(name = "photocomment_insertTime", columnDefinition = "datetime default now()")
    private LocalDateTime pcommentInsertTime;

    /*수정일*/
    @Column(name = "photocomment_updateTime")
    private LocalDateTime pcommentUpdateTime;

    //댓글 단 글
    @ManyToOne
    @JoinColumn(name = "photoboard_no")
    private PhotoBoard pboardNo;
}
