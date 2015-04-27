package com.tadpole.vo.search;

import java.io.Serializable;
import java.util.Date;

public class SmsTemplateSearchVo implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String name;
	protected String content;

	public String getName() {

		return name;
	}
	
	public String getContent() {

		return content;
	}
	
	public void setName(String name) {

		this.name = name;
	}
	
	public void setContent(String content) {

		this.content = content;
	}
	
}
