package com.tadpole.controller;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import net.sf.ezmorph.object.DateMorpher;

import org.apache.commons.lang.StringUtils;

import com.tadpole.vo.search.SmsTemplateSearchVo;

import com.tadpole.entity.SmsTemplate;
import com.tadpole.service.SmsTemplateService;
import com.tadpole.vo.ResponseVo;
import com.tadpole.vo.PagedElement;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;

@Component("SmsTemplateAction")
@Scope("prototype")
public class SmsTemplateAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	@Resource(name = "SmsTemplateService")
	private SmsTemplateService smsTemplateService;

	public String saveOrUpdateSmsTemplate() {
	
		try {
			String smsTemplateJson = getParameter("smsTemplateJson");

			if (StringUtils.isEmpty(smsTemplateJson)) {
				setResponse(ResponseVo.newFailMessage("Bad request : no data found to save or update."));

				return SUCCESS;
			}
			
			JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd"}));
			
			SmsTemplate smsTemplate = (SmsTemplate)JSONObject.toBean(JSONObject.fromObject(smsTemplateJson), SmsTemplate.class);

			SmsTemplate saved = smsTemplateService.saveOrUpdateSmsTemplate(smsTemplate);
			
			ResponseVo response = ResponseVo.newSuccessMessage("The smsTemplate is successfully saved.");
			response.setObject(saved);
			
			setResponse(response);
			
		} catch (Exception e) {
			
			setResponse(ResponseVo.newFailMessage(e.getMessage()));
		}

		return SUCCESS;
	}

	public String deleteSmsTemplate() {
	
		try {
			String smsTemplateId = getParameter("smsTemplateId");

			if (StringUtils.isEmpty(smsTemplateId)) {
				setResponse(ResponseVo.newFailMessage("Bad request : no data is provided to delete."));

				return SUCCESS;
			}

			smsTemplateService.deleteSmsTemplate(smsTemplateId);
			
			ResponseVo response = ResponseVo.newSuccessMessage("The smsTemplate is successfully deleted.");
			
			setResponse(response);
			
		} catch (Exception e) {
			
			setResponse(ResponseVo.newFailMessage(e.getMessage()));
		}

		return SUCCESS;
	}
	
	public String loadSmsTemplates() {

		try {
			String currentIndex = getParameter("currentIndex");
			if (StringUtils.isEmpty(currentIndex)) {
        		currentIndex = "1";
			}
			
			String smsTemplateSearchVoJson = getParameter("smsTemplateSearchVoJson");
			
			JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd"}));
			
			SmsTemplateSearchVo smsTemplateSearchVo = (SmsTemplateSearchVo)JSONObject.toBean(JSONObject.fromObject(smsTemplateSearchVoJson), SmsTemplateSearchVo.class);
			
			Page<SmsTemplate> smsTemplates = smsTemplateService.loadSmsTemplates(currentIndex, smsTemplateSearchVo);

			PagedElement<SmsTemplate> pageElement = new PagedElement<SmsTemplate>(smsTemplates);

			ResponseVo response = ResponseVo.newSuccessMessage("loaded successfully.");
			response.setObject(pageElement);

			setResponse(response);

		} catch (Exception e) {
			setResponse(ResponseVo.newFailMessage(e.getMessage()));

		}

		return SUCCESS;
	}
}
