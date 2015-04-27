package com.tadpole.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author jjiang
 */
@Entity
@Table(name = "tad_dictionary")
public class Dictionary implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	protected Integer id;
	protected String identity;
	protected String optionText;
	protected String optionValue;
	protected String description;

	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public String getIdentity() {

		return identity;
	}

	public void setIdentity(String identity) {

		this.identity = identity;
	}

	public String getOptionText() {

		return optionText;
	}

	public void setOptionText(String optionText) {

		this.optionText = optionText;
	}

	public String getOptionValue() {

		return optionValue;
	}

	public void setOptionValue(String optionValue) {

		this.optionValue = optionValue;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}
}
