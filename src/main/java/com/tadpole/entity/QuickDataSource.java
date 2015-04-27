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
@Table(name = "tad_quick_datasource")
public class QuickDataSource implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	protected Integer id;

	@Column(nullable = false, unique = true)
	protected String name;

	@Column(nullable = false)
	protected String type;

	protected String keyValues;

	protected String querySql;

	protected String description;
	
	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public String getName() {

		return name;
	}

	public String getType() {

		return type;
	}

	public String getKeyValues() {

		return keyValues;
	}

	public String getQuerySql() {

		return querySql;
	}

	public String getDescription() {

		return description;
	}

	public void setName(String name) {

		this.name = name;
	}

	public void setType(String type) {

		this.type = type;
	}

	public void setKeyValues(String keyValues) {

		this.keyValues = keyValues;
	}

	public void setQuerySql(String querySql) {

		this.querySql = querySql;
	}

	public void setDescription(String description) {

		this.description = description;
	}

}
