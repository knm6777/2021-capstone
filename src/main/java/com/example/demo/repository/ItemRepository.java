package com.example.demo.repository;

import com.example.demo.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface ItemRepository<T extends Item, ID extends Serializable> extends JpaRepository<T, ID> {
    //
    public List<T> findItemByPdNoAndCateNoAndThisCateNo(int pdNo, String cateNo, String thisCateNo);
    public List<T> findAllByThisCateNo(String thisCateNo);
}



