package com.tadpole.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ParameterAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tadpole.service.CommonService;
import com.tadpole.vo.ResponseVo;

public class AbstractAction extends ActionSupport implements ParameterAware {

	private static final long serialVersionUID = 1L;

	@Resource(name = "commonService")
	public CommonService commonService;

	private ResponseVo response;
	Map<String, Object> application;
	Map<String, Object> session;

	private String message;

	private Map<String, String[]> requestParameters = null;

	public String getMessage() {

		return message;
	}

	public void setMessage(String message) {

		this.message = message;
	}

	public AbstractAction() {

	}

	public void setupSession() {

		if (session == null) {
			session = ActionContext.getContext().getSession();
		}
	}

	public void setupApplication() {

		if (application == null) {
			application = ActionContext.getContext().getApplication();
		}
	}

	public void setParameters(Map<String, String[]> parameters) {

		this.requestParameters = parameters;

	}

	public Map<String, String[]> getParameters() {

		if (requestParameters == null) {
			requestParameters = new HashMap<String, String[]>();
		}

		return requestParameters;

	}

	public String getParameter(String key) {

		return ActionUtil.getReqParaByMap(getParameters(), key);
	}

	public String[] getParameters(String key) {

		if (getParameters().size() > 0) {
			return getParameters().get(key);
		} else {
			return null;
		}
	}

	public Map<String, Object> getSession() {

		return session;
	}

	public ResponseVo getResponse() {

		return response;
	}

	public void setResponse(ResponseVo response) {

		this.response = response;
	}

	public void success() {

		ResponseVo response = ResponseVo.newSuccessMessage("Server process it successfully.");

		setResponse(response);
	}

	public void success(String message) {

		ResponseVo response = ResponseVo.newSuccessMessage(message);

		setResponse(response);
	}
}
