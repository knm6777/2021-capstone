package com.example.demo.repository.hashtag;

import com.example.demo.model.hashtag.Hashtag;
import com.example.demo.model.hashtag.HashtagAbs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface HashtagRepository<T extends HashtagAbs, ID extends Serializable> extends JpaRepository<T, ID> {
    List<T> findAllByPdNoAndSubcateNoAndCategoryNoOrderByFrequencyDesc(int pdNo, String subcateNo, String cateNo);
    List<T> findAllByName(String name);




}
