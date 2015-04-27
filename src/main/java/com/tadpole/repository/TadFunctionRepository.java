package com.tadpole.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.tadpole.entity.TadFunction;

public interface TadFunctionRepository extends JpaRepository<TadFunction, Integer>, JpaSpecificationExecutor<TadFunction> {

	TadFunction findByTableName(String tableName);

	TadFunction findByEntityName(String entityName);

	TadFunction findByMenuTitle(String menuTitle);
	
	@Query(value="select a.tableName from TadFunction a")
	List<String> loadAllTableNames();
}
