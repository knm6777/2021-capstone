package com.example.demo.repository.category;

import java.io.Serializable;
import java.util.List;

import com.example.demo.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CategoryRepository<T extends Category, ID extends Serializable> extends JpaRepository<T, ID> {
    public List<T> findAll();
    // 큰 카테고리로 작은 카테고리 가져오기(ex. 수납가구-> 나비장, 선반.. 가져오기)

}



