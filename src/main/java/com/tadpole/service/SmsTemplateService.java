package com.tadpole.service;

import org.springframework.data.domain.Page;

import com.tadpole.entity.SmsTemplate;
import com.tadpole.vo.search.SmsTemplateSearchVo;

public interface SmsTemplateService {

	SmsTemplate saveOrUpdateSmsTemplate(SmsTemplate smsTemplate);

	void deleteSmsTemplate(String smsTemplateId);
	
	Page<SmsTemplate> loadSmsTemplates(String currentIndex, SmsTemplateSearchVo smsTemplateSearchVo);
}
