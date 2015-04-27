package com.tadpole.controller;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.tadpole.entity.TadAttribute;
import com.tadpole.service.TadAttributeService;
import com.tadpole.vo.ResponseVo;

public class TadAttributeAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	@Resource(name = "TadAttributeService")
	private TadAttributeService tadAttributeService;

	public String saveOrUpdateTadAttribute() {

		String tadAttributeJson = getParameter("tadAttributeJson");

		if (StringUtils.isEmpty(tadAttributeJson)) {
			setResponse(ResponseVo.newFailMessage("Bad request : no data found to save or update."));

			return SUCCESS;
		}

		TadAttribute tadAttribute = (TadAttribute)JSONObject.toBean(JSONObject.fromObject(tadAttributeJson), TadAttribute.class);

		tadAttributeService.saveOrUpdateTadAttribute(tadAttribute);

		return SUCCESS;
	}

}
