package com.tadpole.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tadpole.entity.TadStrutsNamespace;

public interface TadStrutsNamespaceRepository extends JpaRepository<TadStrutsNamespace, Integer>, JpaSpecificationExecutor<TadStrutsNamespace> {

}
