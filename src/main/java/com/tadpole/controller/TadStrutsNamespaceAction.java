package com.tadpole.controller;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.tadpole.entity.TadStrutsNamespace;
import com.tadpole.service.TadStrutsNamespaceService;
import com.tadpole.vo.ResponseVo;
import com.tadpole.vo.PagedElement;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;

@Component("TadStrutsNamespaceAction")
@Scope("prototype")
public class TadStrutsNamespaceAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	@Resource(name = "TadStrutsNamespaceService")
	private TadStrutsNamespaceService tadStrutsNamespaceService;

	public String saveOrUpdateTadStrutsNamespace() {
	
		try {
			String tadStrutsNamespaceJson = getParameter("tadStrutsNamespaceJson");

			if (StringUtils.isEmpty(tadStrutsNamespaceJson)) {
				setResponse(ResponseVo.newFailMessage("Bad request : no data found to save or update."));

				return SUCCESS;
			}

			TadStrutsNamespace tadStrutsNamespace = (TadStrutsNamespace)JSONObject.toBean(JSONObject.fromObject(tadStrutsNamespaceJson), TadStrutsNamespace.class);

			TadStrutsNamespace saved = tadStrutsNamespaceService.saveOrUpdateTadStrutsNamespace(tadStrutsNamespace);
			
			ResponseVo response = ResponseVo.newSuccessMessage("The tadStrutsNamespace is successfully saved.");
			response.setObject(saved);
			
			setResponse(response);
			
		} catch (Exception e) {
			
			setResponse(ResponseVo.newFailMessage(e.getMessage()));
		}

		return SUCCESS;
	}

	public String loadTadStrutsNamespaces() {

		try {
			Page<TadStrutsNamespace> tadStrutsNamespaces = tadStrutsNamespaceService.loadTadStrutsNamespaces();

			PagedElement<TadStrutsNamespace> pageElement = new PagedElement<TadStrutsNamespace>(tadStrutsNamespaces);

			ResponseVo response = ResponseVo.newSuccessMessage("loaded successfully.");
			response.setObject(pageElement);

			setResponse(response);

		} catch (Exception e) {
			setResponse(ResponseVo.newFailMessage(e.getMessage()));

		}

		return SUCCESS;
	}
}
