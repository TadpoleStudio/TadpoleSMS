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
@Table(name = "tad_dev_attribute")
public class TadAttribute implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	protected Integer id;
	
	protected String functionId;
	
	protected String name;
	
	protected String type;
	
	protected String label;
	
	protected Boolean required;
	
	protected Boolean isUnique;
	
	protected Boolean searchable;
	
	protected String optionText;
	
	protected String dataSourceName;
	
	protected String searchTextDataSourceName;
	
	protected Boolean shownInTable;
	
	protected Boolean textLong;
	

	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}
	
	
	public Boolean getIsUnique() {
	
		return isUnique;
	}

	
	public void setIsUnique(Boolean isUnique) {
	
		this.isUnique = isUnique;
	}

	public String getSearchTextDataSourceName() {
	
		return searchTextDataSourceName;
	}

	
	public void setSearchTextDataSourceName(String searchTextDataSourceName) {
	
		this.searchTextDataSourceName = searchTextDataSourceName;
	}

	public String getFunctionId() {

		return functionId;
	}
	
	public String getName() {

		return name;
	}
	
	public String getType() {

		return type;
	}
	
	public String getLabel() {

		return label;
	}
	
	public Boolean getRequired() {

		return required;
	}
	
	public void setFunctionId(String functionId) {

		this.functionId = functionId;
	}
	
	public void setName(String name) {

		this.name = name;
	}
	
	public void setType(String type) {

		this.type = type;
	}
	
	public void setLabel(String label) {

		this.label = label;
	}
	
	public void setRequired(Boolean required) {

		this.required = required;
	}
	
	public Boolean getSearchable() {
	
		return searchable;
	}
	
	public void setSearchable(Boolean searchable) {
	
		this.searchable = searchable;
	}

	
	public String getOptionText() {
	
		return optionText;
	}
	
	public void setOptionText(String optionText) {
	
		this.optionText = optionText;
	}

	
	public String getDataSourceName() {
	
		return dataSourceName;
	}

	
	public void setDataSourceName(String dataSourceName) {
	
		this.dataSourceName = dataSourceName;
	}

	
	public Boolean getShownInTable() {
	
		return shownInTable;
	}

	
	public void setShownInTable(Boolean shownInTable) {
	
		this.shownInTable = shownInTable;
	}

	
	public Boolean getTextLong() {
	
		return textLong;
	}

	
	public void setTextLong(Boolean textLong) {
	
		this.textLong = textLong;
	}

	
	
	
}
