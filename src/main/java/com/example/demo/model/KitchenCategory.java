package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "KitchenCategory")
@DynamicInsert
@DynamicUpdate

@AttributeOverride(name="thisCateNo", column=@Column(name="kitchencate_no"))
public class KitchenCategory extends Category implements Serializable {

}
