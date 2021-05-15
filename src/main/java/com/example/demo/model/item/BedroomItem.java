package com.example.demo.model.item;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "BedroomItem")
@DynamicInsert
@DynamicUpdate

public class BedroomItem extends ItemAbs implements Serializable {

}
