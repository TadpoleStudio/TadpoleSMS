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
@Table(name = "tad_struts_namespace")
public class TadStrutsNamespace implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	protected Integer id;
	
	protected String name;
	
	protected String createBy;
	
	protected String updatedBy;
	

	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public String getName() {

		return name;
	}
	
	public String getCreateBy() {

		return createBy;
	}
	
	public String getUpdatedBy() {

		return updatedBy;
	}
	
	public void setName(String name) {

		this.name = name;
	}
	
	public void setCreateBy(String createBy) {

		this.createBy = createBy;
	}
	
	public void setUpdatedBy(String updatedBy) {

		this.updatedBy = updatedBy;
	}
	
}
