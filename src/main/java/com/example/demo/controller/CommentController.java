package com.example.demo.controller;

import com.example.demo.model.Comment;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 글 번호에 따라 해당 글의 댓글 가져오기
    @GetMapping("/comment/{boardIdx}")
    public List<Comment> getAllComments(@PathVariable Integer boardIdx) {

        return commentService.getCommentById(boardIdx);
    }

    // 댓글 작성
    @PostMapping("/comment")
    public Comment createBoard(@RequestBody Comment comment) {
        return commentService.createComment(comment);
    }

    /*
    // 글 번호와 댓글 번호에 따라 댓글 하나 가져오기
    @GetMapping("/comment/{boardIdx}/{idx}")
    public ResponseEntity<Comment> getBoardByNo(
            @PathVariable Integer idx,  @PathVariable Integer boardIdx) {
    //필요 하면 수정하겠음
    //안필요할것같아서 냅둠
        return commentService.getBoard(idx);
    }
*/

    // update board
    @PutMapping("/comment/{boardIdx}/{idx}")
    public ResponseEntity<Comment> updateCommentByNo(
            @PathVariable Integer idx, @PathVariable Integer boardIdx, @RequestBody Comment comment){

        return commentService.updateComment(idx, boardIdx, comment);
    }

    // delete board by 댓글 id
    @DeleteMapping("/comment/{boardIdx}/{idx}")
    public ResponseEntity<Map<String, Boolean>> deleteCommentByNo(
            @PathVariable Integer idx, @PathVariable Integer boardIdx) {

        return commentService.deleteComment(idx, boardIdx);
    }



}