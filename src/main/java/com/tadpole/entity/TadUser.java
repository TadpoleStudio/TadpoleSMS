package com.tadpole.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Jerry Jiang
 */
@Entity
@Table(name = "tad_test_user")
public class TadUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	protected Integer id;
	
	protected String userName;
	protected Integer age;

	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public String getUserName() {

		return userName;
	}
	
	public Integer getAge() {

		return age;
	}
	
	public void setUserName(String userName) {

		this.userName = userName;
	}
	
	public void setAge(Integer age) {

		this.age = age;
	}
	
}
