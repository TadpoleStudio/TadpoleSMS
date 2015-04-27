package com.tadpole.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * @author Jerry Jiang
 */
@Entity
@Table(name = "tad_dev_function")
public class TadFunction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	protected Integer id;

	protected String tableName;

	protected String entityName;

	protected String strutsNamespace;

	protected String description;

	protected String title;

	protected String menuTitle;

	protected Integer menuId;

	@javax.persistence.Lob
	protected String jpaEntityCode;

	protected String jpaEntityFilePath;

	@Lob
	protected String javaSearchVoCode;

	protected String javaSearchVoFilePath;

	@Lob
	protected String jsVoCode;

	protected String jsVoFilePath;

	@Lob
	protected String jsSearchVoCode;

	protected String jsSearchVoFilePath;

	@Lob
	protected String actionCode;

	protected String actionFilePath;

	@Lob
	protected String repositoryCode;
	
	@Lob
	protected String repositoryAdditionalCode;
	
	protected String repositoryFilePath;

	@Lob
	protected String serviceInterfaceCode;

	protected String serviceInterfaceFilePath;

	@Lob
	protected String serviceImplementationCode;

	protected String serviceImplementationFilePath;

	@Lob
	protected String strutsConfigurationgCode;

	protected String strutsConfigurationgFilePath;

	@Lob
	protected String jspCode;

	protected String jspFilePath;

	@Lob
	protected String restCode;

	protected String restFilePath;

	
	public String getRepositoryAdditionalCode() {
	
		return repositoryAdditionalCode;
	}

	
	public void setRepositoryAdditionalCode(String repositoryAdditionalCode) {
	
		this.repositoryAdditionalCode = repositoryAdditionalCode;
	}

	public String getJavaSearchVoCode() {

		return javaSearchVoCode;
	}

	public void setJavaSearchVoCode(String javaSearchVoCode) {

		this.javaSearchVoCode = javaSearchVoCode;
	}

	public String getJavaSearchVoFilePath() {

		return javaSearchVoFilePath;
	}

	public void setJavaSearchVoFilePath(String javaSearchVoFilePath) {

		this.javaSearchVoFilePath = javaSearchVoFilePath;
	}

	public String getJsSearchVoCode() {

		return jsSearchVoCode;
	}

	public void setJsSearchVoCode(String jsSearchVoCode) {

		this.jsSearchVoCode = jsSearchVoCode;
	}

	public String getJsSearchVoFilePath() {

		return jsSearchVoFilePath;
	}

	public void setJsSearchVoFilePath(String jsSearchVoFilePath) {

		this.jsSearchVoFilePath = jsSearchVoFilePath;
	}

	public Integer getMenuId() {

		return menuId;
	}

	public void setMenuId(Integer menuId) {

		this.menuId = menuId;
	}

	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public String getTableName() {

		return tableName;
	}

	public String getEntityName() {

		return entityName;
	}

	public String getStrutsNamespace() {

		return strutsNamespace;
	}

	public String getDescription() {

		return description;
	}

	public String getTitle() {

		return title;
	}

	public String getMenuTitle() {

		return menuTitle;
	}

	public String getJpaEntityCode() {

		return jpaEntityCode;
	}

	public String getJsVoCode() {

		return jsVoCode;
	}

	public String getActionCode() {

		return actionCode;
	}

	public String getRepositoryCode() {

		return repositoryCode;
	}

	public String getServiceInterfaceCode() {

		return serviceInterfaceCode;
	}

	public String getServiceImplementationCode() {

		return serviceImplementationCode;
	}

	public void setTableName(String tableName) {

		this.tableName = tableName;
	}

	public void setEntityName(String entityName) {

		this.entityName = entityName;
	}

	public void setStrutsNamespace(String strutsNamespace) {

		this.strutsNamespace = strutsNamespace;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public void setMenuTitle(String menuTitle) {

		this.menuTitle = menuTitle;
	}

	public void setJpaEntityCode(String jpaEntityCode) {

		this.jpaEntityCode = jpaEntityCode;
	}

	public void setJsVoCode(String jsVoCode) {

		this.jsVoCode = jsVoCode;
	}

	public void setActionCode(String actionCode) {

		this.actionCode = actionCode;
	}

	public void setRepositoryCode(String repositoryCode) {

		this.repositoryCode = repositoryCode;
	}

	public void setServiceInterfaceCode(String serviceInterfaceCode) {

		this.serviceInterfaceCode = serviceInterfaceCode;
	}

	public void setServiceImplementationCode(String serviceImplementationCode) {

		this.serviceImplementationCode = serviceImplementationCode;
	}

	public String getStrutsConfigurationgCode() {

		return strutsConfigurationgCode;
	}

	public void setStrutsConfigurationgCode(String strutsConfigurationgCode) {

		this.strutsConfigurationgCode = strutsConfigurationgCode;
	}

	public String getJspCode() {

		return jspCode;
	}

	public void setJspCode(String jspCode) {

		this.jspCode = jspCode;
	}

	public String getJpaEntityFilePath() {

		return jpaEntityFilePath;
	}

	public void setJpaEntityFilePath(String jpaEntityFilePath) {

		this.jpaEntityFilePath = jpaEntityFilePath;
	}

	public String getJsVoFilePath() {

		return jsVoFilePath;
	}

	public void setJsVoFilePath(String jsVoFilePath) {

		this.jsVoFilePath = jsVoFilePath;
	}

	public String getActionFilePath() {

		return actionFilePath;
	}

	public void setActionFilePath(String actionFilePath) {

		this.actionFilePath = actionFilePath;
	}

	public String getRepositoryFilePath() {

		return repositoryFilePath;
	}

	public void setRepositoryFilePath(String repositoryFilePath) {

		this.repositoryFilePath = repositoryFilePath;
	}

	public String getServiceInterfaceFilePath() {

		return serviceInterfaceFilePath;
	}

	public void setServiceInterfaceFilePath(String serviceInterfaceFilePath) {

		this.serviceInterfaceFilePath = serviceInterfaceFilePath;
	}

	public String getServiceImplementationFilePath() {

		return serviceImplementationFilePath;
	}

	public void setServiceImplementationFilePath(String serviceImplementationFilePath) {

		this.serviceImplementationFilePath = serviceImplementationFilePath;
	}

	public String getStrutsConfigurationgFilePath() {

		return strutsConfigurationgFilePath;
	}

	public void setStrutsConfigurationgFilePath(String strutsConfigurationgFilePath) {

		this.strutsConfigurationgFilePath = strutsConfigurationgFilePath;
	}

	public String getJspFilePath() {

		return jspFilePath;
	}

	public void setJspFilePath(String jspFilePath) {

		this.jspFilePath = jspFilePath;
	}

	
	public String getRestCode() {
	
		return restCode;
	}

	
	public void setRestCode(String restCode) {
	
		this.restCode = restCode;
	}

	
	public String getRestFilePath() {
	
		return restFilePath;
	}

	
	public void setRestFilePath(String restFilePath) {
	
		this.restFilePath = restFilePath;
	}
	
	

}
