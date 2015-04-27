package com.tadpole.service;

import java.util.List;

import com.tadpole.entity.TadAttribute;
import com.tadpole.entity.TadFunction;


public interface TadFunctionService {

	TadFunction saveOrUpdateTadFunction(TadFunction tadFunction);

	TadFunction generateCode(String functionId, String codeType);

	List<String> loadAllTableNames();

	TadFunction loadSingleFunction(String tableNameSearch);

	List<TadAttribute> loadFunctionAttrites(String functionId);

}
