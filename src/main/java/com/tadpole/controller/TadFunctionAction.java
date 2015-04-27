package com.tadpole.controller;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tadpole.entity.TadAttribute;
import com.tadpole.entity.TadFunction;
import com.tadpole.service.TadAttributeService;
import com.tadpole.service.TadFunctionService;
import com.tadpole.vo.ResponseVo;

@Component("TadFunctionAction")
@Scope("prototype")
public class TadFunctionAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	@Resource(name = "TadFunctionService")
	private TadFunctionService tadFunctionService;

	@Resource(name = "TadAttributeService")
	private TadAttributeService tadAttributeService;

	public String saveOrUpdateTadFunction() {

		String tadFunctionJson = getParameter("tadFunctionJson");

		System.out.println(tadFunctionJson);

		if (StringUtils.isEmpty(tadFunctionJson)) {
			setResponse(ResponseVo.newFailMessage("Bad request : no data found to save or update."));

			return SUCCESS;
		}

		TadFunction tadFunction = (TadFunction)JSONObject.toBean(JSONObject.fromObject(tadFunctionJson), TadFunction.class);

		if (StringUtils.isEmpty(tadFunction.getTableName())) {

			setResponse(ResponseVo.newFailMessage("Bad request : Table Name is required."));
			return SUCCESS;
		}

		if (StringUtils.isEmpty(tadFunction.getEntityName())) {

			setResponse(ResponseVo.newFailMessage("Bad request : Entity Name is required."));
			return SUCCESS;
		}

		if (StringUtils.isEmpty(tadFunction.getStrutsNamespace())) {

			setResponse(ResponseVo.newFailMessage("Bad request : Struts namespace is required."));
			return SUCCESS;
		}

		if (StringUtils.isEmpty(tadFunction.getTitle())) {

			setResponse(ResponseVo.newFailMessage("Bad request : Title is required."));
			return SUCCESS;
		}

		if (StringUtils.isEmpty(tadFunction.getMenuTitle())) {

			setResponse(ResponseVo.newFailMessage("Bad request : Menu title is required."));
			return SUCCESS;
		}

		try {
			TadFunction saved = tadFunctionService.saveOrUpdateTadFunction(tadFunction);

			ResponseVo success = ResponseVo.newSuccessMessage("The function is saved successfully.");
			success.setObject(saved);

			setResponse(success);

		} catch (Exception e) {

			setResponse(ResponseVo.newFailMessage(e.getMessage()));
			return SUCCESS;
		}
		return SUCCESS;
	}

	public String saveOrUpdateTadAttribute() {

		String tadAttributeJson = getParameter("tadAttributeJson");

		if (StringUtils.isEmpty(tadAttributeJson)) {
			setResponse(ResponseVo.newFailMessage("Bad request : no data found to save or update."));

			return SUCCESS;
		}

		TadAttribute tadAttribute = (TadAttribute)JSONObject.toBean(JSONObject.fromObject(tadAttributeJson), TadAttribute.class);
		try {
			
			tadAttributeService.saveOrUpdateTadAttribute(tadAttribute);
		} catch (Exception e) {

			setResponse(ResponseVo.newFailMessage(e.getMessage()));
			return SUCCESS;
		}
		
		return SUCCESS;
	}

	public String removeAttribute() {

		String attributeId = getParameter("attributeId");
		if (StringUtils.isEmpty(attributeId)) {
			setResponse(ResponseVo.newFailMessage("Bad request : no data found to save or update."));

			return SUCCESS;
		}

		try {
			tadAttributeService.removeAttribute(attributeId);

		} catch (Exception e) {
			setResponse(ResponseVo.newFailMessage(e.getMessage()));
		}

		ResponseVo response = ResponseVo.newSuccessMessage("The attribute is successfully removed.");

		setResponse(response);
		return SUCCESS;
	}
	
	public String removeSingleFunction() {
		
		String functionId = getParameter("functionId");
		if (StringUtils.isBlank(functionId)) {
			
			setResponse(ResponseVo.newFailMessage("No function selected."));
			
			return SUCCESS;
		}
		
		tadAttributeService.removeSingleFunction(functionId);
		
		success();
		
		return SUCCESS;
	}

	public String generateCode() {

		String functionId = getParameter("functionId");
		String codeType = getParameter("codeType");

		if (StringUtils.isEmpty(functionId)) {
			setResponse(ResponseVo.newFailMessage("Bad request : no data "));

			return SUCCESS;
		}

		try {
			TadFunction saved = tadFunctionService.generateCode(functionId, codeType);

			ResponseVo success = ResponseVo.newSuccessMessage("The code is generated successfully.");
			success.setObject(saved);

			setResponse(success);

		} catch (Exception e) {
			e.printStackTrace();
			
			setResponse(ResponseVo.newFailMessage(e.getMessage()));
			return SUCCESS;
		}

		return SUCCESS;
	}

	public String loadAllTableNames() {

		List<String> tables = tadFunctionService.loadAllTableNames();

		System.out.println(tables);

		ResponseVo responseVo = ResponseVo.newSuccessMessage("tables loaded.");
		responseVo.setObject(tables);

		setResponse(responseVo);

		return SUCCESS;
	}

	public String loadSingleFunction() {

		String tableNameSearch = getParameter("tableNameSearch");

		if (StringUtils.isEmpty(tableNameSearch)) {
			setResponse(ResponseVo.newFailMessage("Bad request : no table selected."));

			return SUCCESS;
		}

		TadFunction tadFunction = tadFunctionService.loadSingleFunction(tableNameSearch);

		ResponseVo responseVo = ResponseVo.newSuccessMessage("table loaded.");
		responseVo.setObject(tadFunction);

		setResponse(responseVo);
		return SUCCESS;
	}

	public String loadFunctionAttrites() {

		String functionId = getParameter("functionId");

		if (StringUtils.isEmpty(functionId)) {
			setResponse(ResponseVo.newFailMessage("Bad request : no data "));

			return SUCCESS;
		}

		try {
			List<TadAttribute> attributes = tadFunctionService.loadFunctionAttrites(functionId);

			ResponseVo success = ResponseVo.newSuccessMessage("The code is generated successfully.");
			success.setObject(attributes);

			setResponse(success);

		} catch (Exception e) {
			setResponse(ResponseVo.newFailMessage(e.getMessage()));
			return SUCCESS;
		}

		return SUCCESS;
	}

}
