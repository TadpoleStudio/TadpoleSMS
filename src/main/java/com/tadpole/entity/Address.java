package com.tadpole.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Column;

/**
 * @author Jerry Jiang
 */
@Entity
@Table(name = "tad_address")
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	protected Integer id;
	
	protected String street;
	
	protected String city;
	
	protected String province;
	

	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public String getStreet() {

		return street;
	}
	
	public String getCity() {

		return city;
	}
	
	public String getProvince() {

		return province;
	}
	
	public void setStreet(String street) {

		this.street = street;
	}
	
	public void setCity(String city) {

		this.city = city;
	}
	
	public void setProvince(String province) {

		this.province = province;
	}
	
}
