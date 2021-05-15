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
@Table(name = "AdjectiveHashtag")

@DynamicInsert
@DynamicUpdate
@AttributeOverride(name="id", column=@Column(name="adject_id"))
@AttributeOverride(name="name", column=@Column(name="adject_name"))
@AttributeOverride(name="frequency", column=@Column(name="adject_frequency"))
public class AdjectiveHashtag extends HashtagAbs {
}
