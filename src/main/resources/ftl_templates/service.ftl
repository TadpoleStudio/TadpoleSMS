package com.tadpole.service;

import org.springframework.data.domain.Page;

import com.tadpole.entity.${javaClassName};
import com.tadpole.vo.search.${javaClassName}SearchVo;

public interface ${javaClassName}Service {

	${javaClassName} saveOrUpdate${javaClassName}(${javaClassName} ${firstLetterLowerCaseJavaClassName});

	void delete${javaClassName}(String ${firstLetterLowerCaseJavaClassName}Id);
	
	Page<${javaClassName}> load${javaClassName}s(String currentIndex, ${javaClassName}SearchVo ${firstLetterLowerCaseJavaClassName}SearchVo);
}
