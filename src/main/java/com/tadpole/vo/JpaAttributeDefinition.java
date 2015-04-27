package com.tadpole.vo;


public class JpaAttributeDefinition {

	private String name;
	private String type;
	private String length;
	private Boolean searchable;
	private String firstLetterUpperName;
	private String columnAttributes;
	private Boolean textLong;

	public JpaAttributeDefinition(String name, String type, Boolean searchable) {

		super();
		this.name = name;
		this.type = type;

		String firstLetter = name.substring(0, 1);

		this.firstLetterUpperName = firstLetter.toUpperCase() + name.substring(1);
		
		this.searchable = searchable;

	}

	public JpaAttributeDefinition(String name, String type, String length, Boolean searchable) {

		this(name, type, searchable);

		this.length = length;
	}

	
	public String getColumnAttributes() {
	
		return columnAttributes;
	}

	
	public void setColumnAttributes(String columnAttributes) {
	
		this.columnAttributes = columnAttributes;
	}

	public Boolean getSearchable() {
	
		return searchable;
	}

	
	public void setSearchable(Boolean searchable) {
	
		this.searchable = searchable;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getType() {

		return type;
	}

	public void setType(String type) {

		this.type = type;
	}

	public String getFirstLetterUpperName() {

		return firstLetterUpperName;
	}

	public void setFirstLetterUpperName(String firstLetterUpperName) {

		this.firstLetterUpperName = firstLetterUpperName;
	}

	public String getLength() {

		return length;
	}

	public void setLength(String length) {

		this.length = length;
	}

	
	public Boolean getTextLong() {
	
		return textLong;
	}

	
	public void setTextLong(Boolean textLong) {
	
		this.textLong = textLong;
	}

}
