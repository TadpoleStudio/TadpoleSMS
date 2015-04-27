package com.tadpole.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tadpole.entity.QuickDataSource;

public interface QuickDataSourceRepository extends JpaRepository<QuickDataSource, Integer>, JpaSpecificationExecutor<QuickDataSource> {

	/********************************** additional code start line *******************************************************************/
	
	QuickDataSource findByName(String dataSourceName);
	
}
