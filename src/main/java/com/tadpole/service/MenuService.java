package com.tadpole.service;

import org.springframework.data.domain.Page;

import com.tadpole.entity.Menu;
import com.tadpole.vo.search.MenuSearchVo;

public interface MenuService {

	Menu saveOrUpdateMenu(Menu menu);

	void deleteMenu(String menuId);
	
	Page<Menu> loadMenus(String currentIndex, MenuSearchVo menuSearchVo);
}
