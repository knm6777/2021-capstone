package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    public List<Board> findAllByOrderByIdxDesc();
    public List<Board> findAllByTitleIgnoreCaseContaining(String searchKeyword);
    public List<Board> findAllByContentIgnoreCaseContaining(String searchKeyword);
    public List<Board> findAllByWriterIgnoreCaseContaining(String searchKeyword);
    public List<Board> findAllByTitleOrContentOrWriterIgnoreCaseContaining(String searchKeyword1, String searchKeyword2, String searchKeyword3);

}