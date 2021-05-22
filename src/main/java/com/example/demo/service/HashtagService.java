package com.example.demo.service;

import com.example.demo.model.hashtag.AdjectiveHashtag;
import com.example.demo.model.hashtag.NounHashtag;
import com.example.demo.repository.hashtag.AdjectRepository;
import com.example.demo.repository.hashtag.NounRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HashtagService {
    @Autowired
    private NounRepository nounRepository;

    @Autowired
    private AdjectRepository adjectRepository;

    //상품으로 해시태그 찾기
    public List<NounHashtag> getNounHashtagByProduct(int pdNo, String subcateNo, String cateNo){
        return nounRepository.findAllByPdNoAndSubcateNoAndCategoryNoOrderByFrequencyDesc(pdNo, subcateNo, cateNo);
    }
    public List<AdjectiveHashtag> getAdjHashtagByProduct(int pdNo, String subcateNo, String cateNo){
        return adjectRepository.findAllByPdNoAndSubcateNoAndCategoryNoOrderByFrequencyDesc(pdNo, subcateNo, cateNo);

    }

    //top5 해시태그
    public List<NounHashtag> getNounTagTop15(int pdNo, String subcateNo, String cateNo){
        return nounRepository.getNounTagTop5(pdNo, subcateNo, cateNo);
    }
    public List<AdjectiveHashtag> getAdjTagTop5(int pdNo, String subcateNo, String cateNo){
        return adjectRepository.getAdjTagTop5(pdNo, subcateNo, cateNo);
    }


    //해시태그에 해당하는 상품 찾기
    public List<NounHashtag> getProductByNounHashtag(String hashtag){
        return nounRepository.findAllByName(hashtag);
    }

    public List<AdjectiveHashtag> getProductByAdjHashtag(String hashtag){
        return adjectRepository.findAllByName(hashtag);
    }



}
