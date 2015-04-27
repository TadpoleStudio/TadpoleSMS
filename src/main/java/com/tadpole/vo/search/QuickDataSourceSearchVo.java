package com.tadpole.vo.search;

import java.io.Serializable;
import java.util.Date;

public class QuickDataSourceSearchVo implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String name;
	protected String type;
	protected String keyValues;
	protected String querySql;
	protected String description;

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
