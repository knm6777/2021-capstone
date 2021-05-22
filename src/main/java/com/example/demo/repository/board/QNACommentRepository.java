package com.example.demo.repository.board;

import com.example.demo.model.board.QNABoard;
import com.example.demo.model.board.QNAComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QNACommentRepository extends JpaRepository<QNAComment, Integer> {
    //public List<Comment> findAllByOrderByIdxDesc();
    public List<QNAComment> findAllByQboardNo(QNABoard qBoard);
    public List<QNAComment> findAllByQcommentWriter(String writer);
    public abstract java.util.Optional<QNAComment> findByQcommentNoAndQboardNo(Integer qcommentNo, QNABoard qnaBoard);
}