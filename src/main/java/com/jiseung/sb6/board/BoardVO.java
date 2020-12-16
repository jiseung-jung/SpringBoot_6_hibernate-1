package com.jiseung.sb6.board;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@MappedSuperclass
public class BoardVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long num;
	
	@Column
	private String title;
	private String writer;
	private String contents;
	@Column(updatable = false) //수정할 때 애는 제외하고 수정
	@CreationTimestamp
	private Date regDate;
	private long hit;

}
