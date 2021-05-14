package com.example.demo.repository.board;


import com.example.demo.model.PhotoBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhotoBoardRepository extends JpaRepository<PhotoBoard, Integer> {
    public List<PhotoBoard> findAllByOrderByPboardNoDesc();
    public List<PhotoBoard> findAllByPboardTitleIgnoreCaseContaining(String searchKeyword);
    public List<PhotoBoard> findAllByPboardContentIgnoreCaseContaining(String searchKeyword);
    public List<PhotoBoard> findAllByPboardWriterIgnoreCaseContaining(String searchKeyword);


}
