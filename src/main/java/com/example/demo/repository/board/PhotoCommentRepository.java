package com.example.demo.repository.board;

import com.example.demo.model.board.PhotoBoard;
import com.example.demo.model.board.PhotoComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoCommentRepository extends JpaRepository<PhotoComment, Integer> {

    public List<PhotoComment> findAllByPboardNo(PhotoBoard pBoard);
    public abstract java.util.Optional<PhotoComment> findByPcommentNoAndPboardNo(Integer pcommentNo, PhotoBoard pBoard);



}
