package com.example.demo.repository;

import com.example.demo.model.BedroomItem;
import com.example.demo.model.Item;
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
    T findItemByPdNoAndCateNoAndThisCateNo(int pdNo, String cateNo, String thisCateNo);
    List<T> findAllByThisCateNo(String thisCateNo);
    List<T> findAllByThisCateNoOrPdTitleIgnoreCaseContainingOrPdMallIgnoreCaseContaining(String thisCate, String searchKeyword, String searchKeyword1);

    List<T> findAllByCateNoIgnoreCaseContainingOrThisCateNoIgnoreCaseContainingOrPdTitleIgnoreCaseContainingOrPdMallIgnoreCaseContaining(String searchKeyword1, String searchKeyword2, String searchKeyword3, String searchKeyword4);


    public default List<Item> searchAllItem(String searchKeyword){
        List<T> list = findAllByCateNoIgnoreCaseContainingOrThisCateNoIgnoreCaseContainingOrPdTitleIgnoreCaseContainingOrPdMallIgnoreCaseContaining(searchKeyword, searchKeyword, searchKeyword, searchKeyword);
        List<Item> item = new ArrayList<>();
        if(!list.isEmpty()){
            for(T it : list){
                Item temp = new Item();
                temp.setPdNo(it.getPdNo());
                temp.setCateNo(it.getCateNo());
                temp.setThisCateNo(it.getThisCateNo());
                temp.setPdTitle(it.getPdTitle());
                temp.setPdHref(it.getPdHref());
                temp.setPdImg(it.getPdImg());
                temp.setPdPrice(it.getPdPrice());
                temp.setPdMall(it.getPdMall());
                item.add(temp);
            }
        }
        return item;
    }

    public default List<Item> searchItemByCate(String thiscate, String searchKeyword){
        List<T> list = findAllByThisCateNoOrPdTitleIgnoreCaseContainingOrPdMallIgnoreCaseContaining(thiscate, searchKeyword, searchKeyword);
        List<Item> item = new ArrayList<>();
        if(!list.isEmpty()){
            for(T it : list){
                Item temp = new Item();
                temp.setPdNo(it.getPdNo());
                temp.setCateNo(it.getCateNo());
                temp.setThisCateNo(it.getThisCateNo());
                temp.setPdTitle(it.getPdTitle());
                temp.setPdHref(it.getPdHref());
                temp.setPdImg(it.getPdImg());
                temp.setPdPrice(it.getPdPrice());
                temp.setPdMall(it.getPdMall());
                item.add(temp);
            }
        }
        return item;
    }
}



