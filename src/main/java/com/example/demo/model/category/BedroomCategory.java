package com.example.demo.model.category;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "BedroomCategory")
@DynamicInsert
@DynamicUpdate

public class BedroomCategory extends Category {

}
