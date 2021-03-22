package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Board;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    public List<Board> findAllByOrderByIdxDesc();
    public List<Board> findAllByTitleIgnoreCaseContaining(String searchKeyword);
    public List<Board> findAllByContentIgnoreCaseContaining(String searchKeyword);
    public List<Board> findAllByWriterIgnoreCaseContaining(String searchKeyword);
    public List<Board> findAllByTitleOrContentOrWriterIgnoreCaseContaining(String searchKeyword1, String searchKeyword2, String searchKeyword3);

    public final static String SELECT_BOARD_LIST_PAGED = ""
            + "SELECT "
            + "idx,"
            + "title,"
            + "content,"
            + "writer,"
            + "view_cnt,"
            + "notice_yn,"
            + "secret_yn,"
            + "delete_yn,"
            + "insert_time,"
            + "update_time,"
            + "delete_time"
            + " FROM tb_board WHERE 0 < idx "
            + "ORDER BY idx DESC LIMIT ?1, ?2";


    @Query(value = SELECT_BOARD_LIST_PAGED, nativeQuery = true)
    public List<Board> findFromTo(
            final Integer objectStartNum,
            final Integer objectEndNum);
}