package com.tadpole.vo.search;

import java.io.Serializable;
import java.util.Date;

public class ${javaClassName}SearchVo implements Serializable {

	private static final long serialVersionUID = 1L;

<#list attributeDefinitions as attr>
	<#if attr.searchable??>
	protected ${attr.type} ${attr.name};
	</#if>
</#list>

<#list attributeDefinitions as attr>
	<#if attr.searchable??>
	public ${attr.type} get${attr.firstLetterUpperName}() {

		return ${attr.name};
	}
	
	</#if>
</#list>
<#list attributeDefinitions as attr>
	<#if attr.searchable??>
	public void set${attr.firstLetterUpperName}(${attr.type} ${attr.name}) {

		this.${attr.name} = ${attr.name};
	}
	
	</#if>
</#list>
}
