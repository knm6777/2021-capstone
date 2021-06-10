package com.example.demo.model;


import lombok.Getter;
import lombok.Setter;

//프론트 전달용 db저장 안함
@Getter
@Setter
public class Recommend {

    int pdNo;

    String subcateNo;

    String cateNo;

    Double similarity;
}
