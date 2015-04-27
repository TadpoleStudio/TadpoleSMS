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

import com.tadpole.entity.SmsTemplate;
import com.tadpole.repository.SmsTemplateRepository;
import com.tadpole.service.SmsTemplateService;
import com.tadpole.vo.search.SmsTemplateSearchVo;

@Service("SmsTemplateService")
public class SmsTemplateServiceImpl implements SmsTemplateService {

	@Autowired
	SmsTemplateRepository smsTemplateRepository;

	public SmsTemplate saveOrUpdateSmsTemplate(SmsTemplate smsTemplate) {

		return smsTemplateRepository.saveAndFlush(smsTemplate);
	}
	
	public void deleteSmsTemplate(String smsTemplateId) {

		smsTemplateRepository.delete(Integer.valueOf(smsTemplateId));
	}
	
	public Page<SmsTemplate> loadSmsTemplates(String currentIndex, SmsTemplateSearchVo smsTemplateSearchVo) {
		Specifications<SmsTemplate> smsTemplateSpecification = null;
		if (StringUtils.isNotBlank(smsTemplateSearchVo.getName())) {
			
       	 	smsTemplateSpecification = Specifications.where(smsTemplateNameSpecification(smsTemplateSearchVo.getName()));
		}

		if (smsTemplateSpecification == null) {
       		return smsTemplateRepository.findAll(new PageRequest(Integer.valueOf(currentIndex) - 1, 10));
		} else {
        	return smsTemplateRepository.findAll(smsTemplateSpecification, new PageRequest(Integer.valueOf(currentIndex) - 1, 10));
		}		
		
	}

	private Specification<SmsTemplate> smsTemplateNameSpecification(final String name) {

		return new Specification<SmsTemplate>(){

			public Predicate toPredicate(Root<SmsTemplate> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				return cb.equal(root.get("name"), name);
			}
		};
	}
}
