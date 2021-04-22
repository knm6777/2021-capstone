package com.example.demo.model;

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

@AttributeOverride(name="thisCateNo", column=@Column(name="bedcate_no"))
public class BedroomCategory extends Category {

}
