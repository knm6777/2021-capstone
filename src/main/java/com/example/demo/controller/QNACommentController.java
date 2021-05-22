package com.example.demo.controller;

import com.example.demo.model.board.QNAComment;
import com.example.demo.service.QNACommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/comment")
public class QNACommentController {

    @Autowired
    private QNACommentService QNACommentService;

    // 글 번호에 따라 해당 글의 댓글 가져오기
    @GetMapping("/{qboardNo}")
    @PreAuthorize("permitAll()")
    public List<QNAComment> getAllComments(@PathVariable Integer qboardNo) {

        return QNACommentService.getCommentById(qboardNo);
    }

    // 댓글 작성
    @PostMapping("/{qboardNo}")
    @PreAuthorize("permitAll()")
    public QNAComment createComment(@RequestBody QNAComment QNAComment, @PathVariable Integer qboardNo) {
        return QNACommentService.createComment(QNAComment, qboardNo);
    }

    //한 유저가 작성한 댓글
    @GetMapping("/writer")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<QNAComment>> getQnaCommentByWriter(@RequestParam String writer){
        return ResponseEntity.ok(QNACommentService.getQnaCommentByWriter(writer));
    }


    // update board
    @PutMapping("/{qboardNo}/{qcommentNo}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<QNAComment> updateCommentByNo(
            @PathVariable Integer qcommentNo, @PathVariable Integer qboardNo, @RequestBody QNAComment QNAComment){

        return QNACommentService.updateComment(qcommentNo, qboardNo, QNAComment);
    }

    // delete board by 댓글 id
    @DeleteMapping("/{qboardNo}/{qcommentNo}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Map<String, Boolean>> deleteCommentByNo(
            @PathVariable Integer qcommentNo, @PathVariable Integer qboardNo) {

        return QNACommentService.deleteComment(qcommentNo, qboardNo);
    }



}