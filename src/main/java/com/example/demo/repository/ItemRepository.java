package com.example.demo.repository;

import com.example.demo.model.BedroomItem;
import com.example.demo.model.ItemAbs;
import com.example.demo.model.LibraryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoRepositoryBean
public interface ItemRepository<T extends ItemAbs, ID extends Serializable> extends JpaRepository<T, ID> {
    //
    public List<T> findItemByPdNoAndCateNoAndThisCateNo(int pdNo, String cateNo, String thisCateNo);
    public List<T> findAllByThisCateNo(String thisCateNo);

    List<T> findAllByCateNoIgnoreCaseContaining(String searchKeyword);
    List<T> findAllByThisCateNoIgnoreCaseContaining(String searchKeyword);
    List<T> findAllByPdTitleIgnoreCaseContaining(String searchKeyword);
    List<T> findAllByPdMallIgnoreCaseContaining(String searchKeyword);


    public default List<T> searchItem(String searchKeyword){
        List<T> result = new ArrayList<>();
        List<T> list1 = findAllByCateNoIgnoreCaseContaining(searchKeyword);
        if(!list1.isEmpty()){
            result.addAll(list1);
        }
        List<T> list2 = findAllByThisCateNoIgnoreCaseContaining(searchKeyword);
        if(!list2.isEmpty()){
            result.addAll(list2);
        }
        List<T> list3 = findAllByPdTitleIgnoreCaseContaining(searchKeyword);
        if(!list3.isEmpty()){
            result.addAll(list3);
        }
        List<T> list4 = findAllByPdMallIgnoreCaseContaining(searchKeyword);
        if(!list4.isEmpty()){
            result.addAll(list4);
        }

        return result;
    }

}



