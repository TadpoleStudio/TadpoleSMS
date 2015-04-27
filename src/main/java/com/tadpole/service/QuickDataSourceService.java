package com.tadpole.service;

import org.springframework.data.domain.Page;

import com.tadpole.entity.QuickDataSource;
import com.tadpole.vo.search.QuickDataSourceSearchVo;

public interface QuickDataSourceService {

	QuickDataSource saveOrUpdateQuickDataSource(QuickDataSource quickDataSource);

	void deleteQuickDataSource(String quickDataSourceId);
	
	Page<QuickDataSource> loadQuickDataSources(String currentIndex, QuickDataSourceSearchVo quickDataSourceSearchVo);
}
