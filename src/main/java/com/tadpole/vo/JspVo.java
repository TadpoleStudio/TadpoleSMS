package com.tadpole.vo;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.tadpole.entity.TadAttribute;

public class JspVo {

	private String javaClassName;
	private String firstLetterLowerCaseJavaClassName;
	private String strutsNamespace;
	private String title;
	private List<JpaAttributeDefinition> attributeDefinitions;
	private List<List<JpaAttributeDefinition>> attrGroupList;
	private List<TadAttribute> tadAttributes;
	private List<TadAttribute> searchableTadAttributes;

	private Set<String> datasourceNames = Sets.newHashSet();
	
	public List<TadAttribute> getSearchableTadAttributes() {
	
		return searchableTadAttributes;
	}

	
	public void setSearchableTadAttributes(List<TadAttribute> searchableTadAttributes) {
	
		this.searchableTadAttributes = searchableTadAttributes;
	}

	public String getJavaClassName() {
	
		return javaClassName;
	}
	
	public void setJavaClassName(String javaClassName) {
	
		this.javaClassName = javaClassName;
	}
	
	public String getFirstLetterLowerCaseJavaClassName() {
	
		return firstLetterLowerCaseJavaClassName;
	}
	
	public void setFirstLetterLowerCaseJavaClassName(String firstLetterLowerCaseJavaClassName) {
	
		this.firstLetterLowerCaseJavaClassName = firstLetterLowerCaseJavaClassName;
	}
	
	public String getStrutsNamespace() {
	
		return strutsNamespace;
	}
	
	public void setStrutsNamespace(String strutsNamespace) {
	
		this.strutsNamespace = strutsNamespace;
	}
	
	public String getTitle() {
	
		return title;
	}
	
	public void setTitle(String title) {
	
		this.title = title;
	}
	
	public List<JpaAttributeDefinition> getAttributeDefinitions() {
	
		return attributeDefinitions;
	}
	
	public void setAttributeDefinitions(List<JpaAttributeDefinition> attributeDefinitions) {
	
		this.attributeDefinitions = attributeDefinitions;
	}
	
	public List<List<JpaAttributeDefinition>> getAttrGroupList() {
	
		return attrGroupList;
	}
	
	public void setAttrGroupList(List<List<JpaAttributeDefinition>> attrGroupList) {
	
		this.attrGroupList = attrGroupList;
	}

	
	public List<TadAttribute> getTadAttributes() {
	
		return tadAttributes;
	}

	
	public void setTadAttributes(List<TadAttribute> tadAttributes) {
	
		this.tadAttributes = tadAttributes;
	}

	
	public Set<String> getDatasourceNames() {
	
		return datasourceNames;
	}

	
	public void setDatasourceNames(Set<String> datasourceNames) {
	
		this.datasourceNames = datasourceNames;
	}
	
}
