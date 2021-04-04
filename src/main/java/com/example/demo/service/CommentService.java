package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;

import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    // 글번호 id인 글의 댓글들 가져오기
    public List<Comment> getCommentById(Integer boardIdx) {
        return commentRepository.findAllByBoardIdx(boardIdx);
    }

    // create comment
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    // update comment
    public ResponseEntity<Comment> updateComment(Integer idx, Integer boardIdx, Comment updatedComment) {
        Comment comment = commentRepository.findByIdxAndBoardIdx(idx, boardIdx)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Comment Data by idx : ["+idx+"]"));
        comment.setWriter(updatedComment.getWriter());
        comment.setContent(updatedComment.getContent());
        comment.setUpdateTime(LocalDateTime.now());

        Comment endUpdatedComment = commentRepository.save(comment);
        return ResponseEntity.ok(endUpdatedComment);
    }

    // delete comment 댓글번호를 통해
    public ResponseEntity<Map<String, Boolean>> deleteComment(Integer idx, Integer boardIdx) {
        Comment comment = commentRepository.findByIdxAndBoardIdx(idx, boardIdx)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Comment Data by idx : ["+idx+"]"));

        commentRepository.delete(comment);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted Comment Data by id : ["+idx+"]", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
