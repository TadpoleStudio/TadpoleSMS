package com.tadpole.controller;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import net.sf.ezmorph.object.DateMorpher;

import org.apache.commons.lang.StringUtils;

import com.tadpole.vo.search.MenuSearchVo;

import com.tadpole.entity.Menu;
import com.tadpole.service.MenuService;
import com.tadpole.vo.ResponseVo;
import com.tadpole.vo.PagedElement;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;

@Component("MenuAction")
@Scope("prototype")
public class MenuAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	@Resource(name = "MenuService")
	private MenuService menuService;

	public String saveOrUpdateMenu() {
	
		try {
			String menuJson = getParameter("menuJson");

			if (StringUtils.isEmpty(menuJson)) {
				setResponse(ResponseVo.newFailMessage("Bad request : no data found to save or update."));

				return SUCCESS;
			}
			
			JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd"}));
			
			Menu menu = (Menu)JSONObject.toBean(JSONObject.fromObject(menuJson), Menu.class);

			Menu saved = menuService.saveOrUpdateMenu(menu);
			
			ResponseVo response = ResponseVo.newSuccessMessage("The menu is successfully saved.");
			response.setObject(saved);
			
			setResponse(response);
			
		} catch (Exception e) {
			
			setResponse(ResponseVo.newFailMessage(e.getMessage()));
		}

		return SUCCESS;
	}

	public String deleteMenu() {
	
		try {
			String menuId = getParameter("menuId");

			if (StringUtils.isEmpty(menuId)) {
				setResponse(ResponseVo.newFailMessage("Bad request : no data is provided to delete."));

				return SUCCESS;
			}

			menuService.deleteMenu(menuId);
			
			ResponseVo response = ResponseVo.newSuccessMessage("The menu is successfully deleted.");
			
			setResponse(response);
			
		} catch (Exception e) {
			
			setResponse(ResponseVo.newFailMessage(e.getMessage()));
		}

		return SUCCESS;
	}
	public String loadMenus() {

		try {
			String currentIndex = getParameter("currentIndex");
			if (StringUtils.isEmpty(currentIndex)) {
        		currentIndex = "1";
			}
			
			String menuSearchVoJson = getParameter("menuSearchVoJson");
			MenuSearchVo menuSearchVo = (MenuSearchVo)JSONObject.toBean(JSONObject.fromObject(menuSearchVoJson), MenuSearchVo.class);
			
			Page<Menu> menus = menuService.loadMenus(currentIndex, menuSearchVo);

			PagedElement<Menu> pageElement = new PagedElement<Menu>(menus);

			ResponseVo response = ResponseVo.newSuccessMessage("loaded successfully.");
			response.setObject(pageElement);

			setResponse(response);

		} catch (Exception e) {
			setResponse(ResponseVo.newFailMessage(e.getMessage()));

		}

		return SUCCESS;
	}
}
