package com.example.demo.repository.hashtag;

import com.example.demo.model.hashtag.NounHashtag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface NounRepository extends HashtagRepository<NounHashtag, Integer> {

    String SELECT_TOP_15=""
            + "SELECT "
            + "noun_id, "
            + "noun_name, "
            + "noun_frequency,"
            + "pd_no, "
            + "subcate_no, "
            + "category_no "
            + "FROM NounHashtag "
            + "WHERE pd_no = ?1 AND subcate_no = ?2 AND category_no = ?3 "
            + "ORDER BY noun_frequency "
            + "DESC LIMIT 15";
    @Query(value = SELECT_TOP_15, nativeQuery = true)
    List<NounHashtag> getNounTagTop5(int pdNo, String subcateNo, String cateNo);
}
