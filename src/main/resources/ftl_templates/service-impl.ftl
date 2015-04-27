package com.tadpole.service.impl;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.tadpole.entity.${javaClassName};
import com.tadpole.repository.${javaClassName}Repository;
import com.tadpole.service.${javaClassName}Service;
import com.tadpole.vo.search.${javaClassName}SearchVo;

@Service("${javaClassName}Service")
public class ${javaClassName}ServiceImpl implements ${javaClassName}Service {

	@Autowired
	${javaClassName}Repository ${firstLetterLowerCaseJavaClassName}Repository;

	public ${javaClassName} saveOrUpdate${javaClassName}(${javaClassName} ${firstLetterLowerCaseJavaClassName}) {

		return ${firstLetterLowerCaseJavaClassName}Repository.saveAndFlush(${firstLetterLowerCaseJavaClassName});
	}
	
	public void delete${javaClassName}(String ${firstLetterLowerCaseJavaClassName}Id) {

		${firstLetterLowerCaseJavaClassName}Repository.delete(Integer.valueOf(${firstLetterLowerCaseJavaClassName}Id));
	}
	
	public Page<${javaClassName}> load${javaClassName}s(String currentIndex, ${javaClassName}SearchVo ${firstLetterLowerCaseJavaClassName}SearchVo) {
		Specifications<${javaClassName}> ${firstLetterLowerCaseJavaClassName}Specification = null;
<#list attributeDefinitions as attr>
	<#if attr.searchable?? && attr.searchable>
		<#if attr.type == 'String'>
		if (StringUtils.isNotBlank(${firstLetterLowerCaseJavaClassName}SearchVo.get${attr.firstLetterUpperName}())) {
			<#else>
		if (${firstLetterLowerCaseJavaClassName}SearchVo.get${attr.firstLetterUpperName}() != null) {
			</#if>	
		<#if attr_index == 0>
			
       	 	${firstLetterLowerCaseJavaClassName}Specification = Specifications.where(${firstLetterLowerCaseJavaClassName}${attr.firstLetterUpperName}Specification(${firstLetterLowerCaseJavaClassName}SearchVo.get${attr.firstLetterUpperName}()));
		}
		<#else>

         	if (${firstLetterLowerCaseJavaClassName}Specification == null) {
                ${firstLetterLowerCaseJavaClassName}Specification = Specifications.where(${firstLetterLowerCaseJavaClassName}${attr.firstLetterUpperName}Specification(${firstLetterLowerCaseJavaClassName}SearchVo.get${attr.firstLetterUpperName}()));
         	} else {
                ${firstLetterLowerCaseJavaClassName}Specification.and(${firstLetterLowerCaseJavaClassName}${attr.firstLetterUpperName}Specification(${firstLetterLowerCaseJavaClassName}SearchVo.get${attr.firstLetterUpperName}()));
        	}
 		}
		</#if>
	</#if>
</#list>

		if (${firstLetterLowerCaseJavaClassName}Specification == null) {
       		return ${firstLetterLowerCaseJavaClassName}Repository.findAll(new PageRequest(Integer.valueOf(currentIndex) - 1, 10));
		} else {
        	return ${firstLetterLowerCaseJavaClassName}Repository.findAll(${firstLetterLowerCaseJavaClassName}Specification, new PageRequest(Integer.valueOf(currentIndex) - 1, 10));
		}		
		
	}

<#list attributeDefinitions as attr>
	<#if attr.searchable?? && attr.searchable >
	private Specification<${javaClassName}> ${firstLetterLowerCaseJavaClassName}${attr.firstLetterUpperName}Specification(final ${attr.type} ${attr.name}) {

		return new Specification<${javaClassName}>(){

			public Predicate toPredicate(Root<${javaClassName}> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				return cb.equal(root.get("${attr.name}"), ${attr.name});
			}
		};
	}
	</#if>
</#list>
}
