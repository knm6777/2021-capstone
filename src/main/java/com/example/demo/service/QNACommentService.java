package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;

import com.example.demo.model.QNABoard;
import com.example.demo.model.QNAComment;
import com.example.demo.repository.board.QNABoardRepository;
import com.example.demo.repository.board.QNACommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QNACommentService {

    @Autowired
    private QNACommentRepository QNACommentRepository;

    @Autowired
    private QNABoardRepository qnaBoardRepository;
    @Autowired
    private QNABoardService qnaBoardService;
    // 글번호 id인 글의 댓글들 가져오기
    public List<QNAComment> getCommentById(Integer qboardNo) {
        QNABoard qnaBoard = qnaBoardRepository.findById(qboardNo)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Photo Data by idx : ["+qboardNo+"]"));

        return QNACommentRepository.findAllByQboardNo(qnaBoard);
    }

    // create comment
    public QNAComment createComment(QNAComment qComment, Integer qboardNo) {

        QNABoard qnaBoard = qnaBoardRepository.findById(qboardNo)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Photo Data by idx : ["+qboardNo+"]"));

        qComment.setQboardNo(qnaBoard);
        QNAComment saveComment = QNACommentRepository.save(qComment);
        qnaBoard.getQboardComments().add(saveComment);

        return  saveComment;
    }

    // update comment
    public ResponseEntity<QNAComment> updateComment(Integer qcommentNo, Integer qboardNo, QNAComment updatedQNAComment) {
        QNABoard qnaBoard = qnaBoardRepository.findById(qboardNo)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Photo Data by idx : ["+qboardNo+"]"));
        QNAComment QNAComment = QNACommentRepository.findByQcommentNoAndQboardNo(qcommentNo, qnaBoard)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Comment Data by qcommentNo : ["+qcommentNo+"]"));
        QNAComment.setQcommentWriter(updatedQNAComment.getQcommentWriter());
        QNAComment.setQcommentContent(updatedQNAComment.getQcommentContent());
        QNAComment.setQcommentUpdateTime(LocalDateTime.now());


        QNAComment endUpdatedQNAComment = QNACommentRepository.save(QNAComment);
        return ResponseEntity.ok(endUpdatedQNAComment);
    }

    // delete comment 댓글번호를 통해
    public ResponseEntity<Map<String, Boolean>> deleteComment(Integer qcommentNo, Integer qboardNo) {
        QNABoard qnaBoard = qnaBoardRepository.findById(qboardNo)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Photo Data by idx : ["+qboardNo+"]"));
        QNAComment QNAComment = QNACommentRepository.findByQcommentNoAndQboardNo(qcommentNo, qnaBoard)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Comment Data by qcommentNo : ["+qcommentNo+"]"));

        QNACommentRepository.delete(QNAComment);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted Comment Data by id : ["+qcommentNo+"]", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
