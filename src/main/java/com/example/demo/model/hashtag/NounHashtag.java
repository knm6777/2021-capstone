package com.example.demo.model.hashtag;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "NounHashtag")

@DynamicInsert
@DynamicUpdate
@AttributeOverride(name="id", column=@Column(name="noun_id"))
@AttributeOverride(name="name", column=@Column(name="noun_name"))
@AttributeOverride(name="frequency", column=@Column(name="noun_frequency"))
public class NounHashtag extends HashtagAbs {
}
