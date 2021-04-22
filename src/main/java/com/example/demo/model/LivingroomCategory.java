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
@Table(name = "LivingroomCategory")
@DynamicInsert
@DynamicUpdate

@AttributeOverride(name="thisCateNo", column=@Column(name="livingcate_no"))
public class LivingroomCategory extends Category implements Serializable {

}
