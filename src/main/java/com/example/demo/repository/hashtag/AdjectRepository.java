package com.example.demo.repository.hashtag;

import com.example.demo.model.hashtag.AdjectiveHashtag;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdjectRepository extends HashtagRepository<AdjectiveHashtag, Integer>{

    String SELECT_TOP_5=""
            + "SELECT "
            + "adject_id, "
            + "adject_name, "
            + "adject_frequency,"
            + "pd_no, "
            + "subcate_no, "
            + "category_no "
            + "FROM AdjectiveHashtag "
            + "WHERE pd_no = ?1 AND subcate_no = ?2 AND category_no = ?3 "
            + "ORDER BY adject_frequency "
            + "DESC LIMIT 5";
    @Query(value = SELECT_TOP_5, nativeQuery = true)
    List<AdjectiveHashtag> getAdjTagTop5(int pdNo, String subcateNo, String cateNo);
}
