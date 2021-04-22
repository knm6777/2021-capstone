package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.QNABoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QNABoardRepository extends JpaRepository<QNABoard, Integer> {
    public QNABoard findTopByOrderByQboardNoDesc();
    public List<QNABoard> findAllByOrderByQboardNoDesc();
    public List<QNABoard> findAllByQboardTitleIgnoreCaseContaining(String searchKeyword);
    public List<QNABoard> findAllByQboardContentIgnoreCaseContaining(String searchKeyword);
    public List<QNABoard> findAllByQboardWriterIgnoreCaseContaining(String searchKeyword);
    public List<QNABoard> findAllByQboardTitleOrQboardContentOrQboardWriterIgnoreCaseContaining(String searchKeyword1, String searchKeyword2, String searchKeyword3);

    public final static String SELECT_BOARD_LIST_PAGED = ""
            + "SELECT "
            + "qboard_no,"
            + "qboard_title,"
            + "qboard_writer,"
            + "qboard_insertTime,"
            + "qboard_views,"
            + "qboard_content,"
            + "qboard_updateTime,"
            + "qboard_fileUrl"
            + " FROM QNABoard WHERE 0 < qboard_no "
            + "ORDER BY qboard_no DESC LIMIT ?1, ?2";


    @Query(value = SELECT_BOARD_LIST_PAGED, nativeQuery = true)
    public List<QNABoard> findFromTo(
            final Integer objectStartNum,
            final Integer objectEndNum);


}