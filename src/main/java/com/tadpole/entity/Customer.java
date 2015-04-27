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
@Table(name = "tad_customer")
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	protected Integer id;
	
	@Column(nullable = false)
	protected String name;
	
	@Column(nullable = false)
	protected String phone;
	
	protected String fixedPhone;
	
	protected String address;
	
	protected String sex;
	
	protected Date birthday;
	
	protected String avatar;
	

	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public String getName() {

		return name;
	}
	
	public String getPhone() {

		return phone;
	}
	
	public String getFixedPhone() {

		return fixedPhone;
	}
	
	public String getAddress() {

		return address;
	}
	
	public String getSex() {

		return sex;
	}
	
	@JSON(format="yyyy-MM-dd")
	public Date getBirthday() {

		return birthday;
	}
	
	public String getAvatar() {

		return avatar;
	}
	
	public void setName(String name) {

		this.name = name;
	}
	
	public void setPhone(String phone) {

		this.phone = phone;
	}
	
	public void setFixedPhone(String fixedPhone) {

		this.fixedPhone = fixedPhone;
	}
	
	public void setAddress(String address) {

		this.address = address;
	}
	
	public void setSex(String sex) {

		this.sex = sex;
	}
	
	public void setBirthday(Date birthday) {

		this.birthday = birthday;
	}
	
	public void setAvatar(String avatar) {

		this.avatar = avatar;
	}
	
}
