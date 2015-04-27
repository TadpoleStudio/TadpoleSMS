package com.tadpole.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.google.common.collect.ImmutableList;
import com.tadpole.entity.QuickDataSource;
import com.tadpole.repository.QuickDataSourceRepository;
import com.tadpole.service.QuickDataSourceService;
import com.tadpole.util.SystemUtils;
import com.tadpole.vo.search.QuickDataSourceSearchVo;

@Service("QuickDataSourceService")
public class QuickDataSourceServiceImpl implements QuickDataSourceService {

	@Autowired
	QuickDataSourceRepository quickDataSourceRepository;

	public QuickDataSource saveOrUpdateQuickDataSource(QuickDataSource quickDataSource) {

		validateQuickDataSource(quickDataSource);

		return quickDataSourceRepository.saveAndFlush(quickDataSource);
	}

	public void deleteQuickDataSource(String quickDataSourceId) {

		quickDataSourceRepository.delete(Integer.valueOf(quickDataSourceId));
	}

	public Page<QuickDataSource> loadQuickDataSources(String currentIndex, QuickDataSourceSearchVo quickDataSourceSearchVo) {

		Specifications<QuickDataSource> quickDataSourceSpecification = null;
		if (StringUtils.isNotBlank(quickDataSourceSearchVo.getName())) {

			quickDataSourceSpecification = Specifications.where(quickDataSourceNameSpecification(quickDataSourceSearchVo.getName()));
		}

		if (quickDataSourceSpecification == null) {
			return quickDataSourceRepository.findAll(new PageRequest(Integer.valueOf(currentIndex) - 1, 10));
		} else {
			return quickDataSourceRepository.findAll(quickDataSourceSpecification, new PageRequest(Integer.valueOf(currentIndex) - 1, 10));
		}

	}

	private Specification<QuickDataSource> quickDataSourceNameSpecification(final String name) {

		return new Specification<QuickDataSource>(){

			public Predicate toPredicate(Root<QuickDataSource> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				return cb.equal(root.get("name"), name);
			}
		};
	}

	private void validateQuickDataSource(QuickDataSource quickDataSource) {

		boolean creating = quickDataSource.getId() == null;
		
		if (creating) {
			List<String> requiredFields = ImmutableList.of("name", "type");
			SystemUtils.requireCheck(requiredFields, quickDataSource);
			
			List<String> uniqueNames = ImmutableList.of("name");

		}

	}

}
