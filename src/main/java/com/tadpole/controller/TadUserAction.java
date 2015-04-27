package com.tadpole.controller;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.tadpole.entity.TadUser;
import com.tadpole.service.TadUserService;
import com.tadpole.vo.ResponseVo;

public class TadUserAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	@Resource(name = "TadUserService")
	private TadUserService tadUserService;

	public String saveOrUpdateTadUser() {

		String tadUserJson = getParameter("tadUserJson");

		if (StringUtils.isEmpty(tadUserJson)) {
			setResponse(ResponseVo.newFailMessage("Bad request : no data found to save or update."));

			return SUCCESS;
		}

		TadUser tadUser = (TadUser)JSONObject.toBean(JSONObject.fromObject(tadUserJson), TadUser.class);

		tadUserService.saveOrUpdateTadUser(tadUser);

		return SUCCESS;
	}

}
