package com.tadpole.vo.search;

import java.io.Serializable;
import java.util.Date;

public class MenuSearchVo implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String title;
	protected String url;
	protected String elementId;
	protected Boolean visible;

	public String getTitle() {

		return title;
	}
	
	public String getUrl() {

		return url;
	}
	
	public String getElementId() {

		return elementId;
	}
	
	public Boolean getVisible() {

		return visible;
	}
	
	public void setTitle(String title) {

		this.title = title;
	}
	
	public void setUrl(String url) {

		this.url = url;
	}
	
	public void setElementId(String elementId) {

		this.elementId = elementId;
	}
	
	public void setVisible(Boolean visible) {

		this.visible = visible;
	}
	
}
