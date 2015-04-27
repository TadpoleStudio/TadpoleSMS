package com.tadpole.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Column;
import org.apache.struts2.json.annotations.JSON;

/**
 * @author Jerry Jiang
 */
@Entity
@Table(name = "tad_sms_template")
public class SmsTemplate implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	protected Integer id;
	
	@Column(unique = true,nullable = false)
	protected String name;
	
	@javax.persistence.Lob
	@Column(nullable = false)
	protected String content;
	

	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public String getName() {

		return name;
	}
	
	public String getContent() {

		return content;
	}
	
	public void setName(String name) {

		this.name = name;
	}
	
	public void setContent(String content) {

		this.content = content;
	}
	
}
