package com.tadpole.vo.search;

import java.io.Serializable;
import java.util.Date;

public class CustomerSearchVo implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String name;
	protected String phone;
	protected String fixedPhone;
	protected String address;
	protected String sex;
	protected Date birthday;
	protected String avatar;

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
