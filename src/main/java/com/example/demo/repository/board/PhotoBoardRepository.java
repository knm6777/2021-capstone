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
    public List<PhotoBoard> findAllByPboardTitleOrPboardContentOrPboardWriterIgnoreCaseContaining(String searchKeyword1, String searchKeyword2, String searchKeyword3);


    public final static String SELECT_PHOTO_LIST_PAGED = ""
            + "SELECT "
            + "photoboard_no,"
            + "photoboard_title,"
            + "photoboard_content,"
            + "photoboard_writer,"
            + "photoboard_views,"
            + "photoboard_insertTime,"
            + "photoboard_updateTime,"
            + "photoboard_fileUrl"
            + " FROM PhotoBoard WHERE 0 < photoboard_no "
            + "ORDER BY photoboard_no DESC LIMIT ?1, ?2";


    @Query(value = SELECT_PHOTO_LIST_PAGED, nativeQuery = true)
    public List<PhotoBoard> findFromTo(
            final Integer objectStartNum,
            final Integer objectEndNum);




}
