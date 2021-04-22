package com.example.demo.repository;

import com.example.demo.model.QNABoard;
import com.example.demo.model.QNAComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QNACommentRepository extends JpaRepository<QNAComment, Integer> {
    //public List<Comment> findAllByOrderByIdxDesc();
    public List<QNAComment> findAllByQboardNo(QNABoard qBoard);
    public abstract java.util.Optional<QNAComment> findByQcommentNoAndQboardNo(Integer qcommentNo, QNABoard qnaBoard);
}