package com.example.demo.repository;

import com.example.demo.model.Board;
import com.example.demo.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    //public List<Comment> findAllByOrderByIdxDesc();
    public List<Comment> findAllByBoardIdx(Integer boardIdx);
    public abstract java.util.Optional<Comment> findByIdxAndBoardIdx(Integer idx, Integer boardIdx);
}