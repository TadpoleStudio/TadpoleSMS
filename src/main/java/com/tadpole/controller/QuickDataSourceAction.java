package com.tadpole.controller;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import net.sf.ezmorph.object.DateMorpher;

import org.apache.commons.lang.StringUtils;

import com.tadpole.vo.search.QuickDataSourceSearchVo;

import com.tadpole.entity.QuickDataSource;
import com.tadpole.service.QuickDataSourceService;
import com.tadpole.vo.ResponseVo;
import com.tadpole.vo.PagedElement;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;

@Component("QuickDataSourceAction")
@Scope("prototype")
public class QuickDataSourceAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	@Resource(name = "QuickDataSourceService")
	private QuickDataSourceService quickDataSourceService;

	public String saveOrUpdateQuickDataSource() {
	
		try {
			String quickDataSourceJson = getParameter("quickDataSourceJson");

			if (StringUtils.isEmpty(quickDataSourceJson)) {
				setResponse(ResponseVo.newFailMessage("Bad request : no data found to save or update."));

				return SUCCESS;
			}
			
			JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd"}));
			
			QuickDataSource quickDataSource = (QuickDataSource)JSONObject.toBean(JSONObject.fromObject(quickDataSourceJson), QuickDataSource.class);

			QuickDataSource saved = quickDataSourceService.saveOrUpdateQuickDataSource(quickDataSource);
			
			ResponseVo response = ResponseVo.newSuccessMessage("The quickDataSource is successfully saved.");
			response.setObject(saved);
			
			setResponse(response);
			
		} catch (Exception e) {
			
			setResponse(ResponseVo.newFailMessage(e.getMessage()));
		}

		return SUCCESS;
	}

	public String deleteQuickDataSource() {
	
		try {
			String quickDataSourceId = getParameter("quickDataSourceId");

			if (StringUtils.isEmpty(quickDataSourceId)) {
				setResponse(ResponseVo.newFailMessage("Bad request : no data is provided to delete."));

				return SUCCESS;
			}

			quickDataSourceService.deleteQuickDataSource(quickDataSourceId);
			
			ResponseVo response = ResponseVo.newSuccessMessage("The quickDataSource is successfully deleted.");
			
			setResponse(response);
			
		} catch (Exception e) {
			
			setResponse(ResponseVo.newFailMessage(e.getMessage()));
		}

		return SUCCESS;
	}
	
	public String loadQuickDataSources() {

		try {
			String currentIndex = getParameter("currentIndex");
			if (StringUtils.isEmpty(currentIndex)) {
        		currentIndex = "1";
			}
			
			String quickDataSourceSearchVoJson = getParameter("quickDataSourceSearchVoJson");
			
			JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd"}));
			
			QuickDataSourceSearchVo quickDataSourceSearchVo = (QuickDataSourceSearchVo)JSONObject.toBean(JSONObject.fromObject(quickDataSourceSearchVoJson), QuickDataSourceSearchVo.class);
			
			Page<QuickDataSource> quickDataSources = quickDataSourceService.loadQuickDataSources(currentIndex, quickDataSourceSearchVo);

			PagedElement<QuickDataSource> pageElement = new PagedElement<QuickDataSource>(quickDataSources);

			ResponseVo response = ResponseVo.newSuccessMessage("loaded successfully.");
			response.setObject(pageElement);

			setResponse(response);

		} catch (Exception e) {
			setResponse(ResponseVo.newFailMessage(e.getMessage()));

		}

		return SUCCESS;
	}
}
