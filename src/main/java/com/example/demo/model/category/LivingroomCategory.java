package com.example.demo.model.category;

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

public class LivingroomCategory extends Category implements Serializable {

}
