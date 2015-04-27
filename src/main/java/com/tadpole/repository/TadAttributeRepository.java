package com.tadpole.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tadpole.entity.TadAttribute;

public interface TadAttributeRepository extends JpaRepository<TadAttribute, Integer>, JpaSpecificationExecutor<TadAttribute> {
	
	List<TadAttribute> findByFunctionId(String functionId);

}
