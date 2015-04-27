package com.tadpole.service.impl;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.tadpole.entity.Menu;
import com.tadpole.repository.MenuRepository;
import com.tadpole.service.MenuService;
import com.tadpole.vo.search.MenuSearchVo;

@Service("MenuService")
public class MenuServiceImpl implements MenuService {

	@Autowired
	MenuRepository menuRepository;

	public Menu saveOrUpdateMenu(Menu menu) {

		return menuRepository.saveAndFlush(menu);
	}
	
	public void deleteMenu(String menuId) {

		menuRepository.delete(Integer.valueOf(menuId));
	}
	
	public Page<Menu> loadMenus(String currentIndex, MenuSearchVo menuSearchVo) {
		Specifications<Menu> menuSpecification = null;
		if (StringUtils.isNotBlank(menuSearchVo.getTitle())) {
			
       	 	menuSpecification = Specifications.where(menuTitleSpecification(menuSearchVo.getTitle()));
		}
		if (StringUtils.isNotBlank(menuSearchVo.getUrl())) {

         	if (menuSpecification == null) {
                menuSpecification = Specifications.where(menuUrlSpecification(menuSearchVo.getUrl()));
         	} else {
                menuSpecification.and(menuUrlSpecification(menuSearchVo.getUrl()));
        	}
 		}

		if (menuSpecification == null) {
       		return menuRepository.findAll(new PageRequest(Integer.valueOf(currentIndex) - 1, 10));
		} else {
        	return menuRepository.findAll(menuSpecification, new PageRequest(Integer.valueOf(currentIndex) - 1, 10));
		}		
		
	}

	private Specification<Menu> menuTitleSpecification(final String title) {

		return new Specification<Menu>(){

			public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				return cb.equal(root.get("title"), title);
			}
		};
	}
	private Specification<Menu> menuUrlSpecification(final String url) {

		return new Specification<Menu>(){

			public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				return cb.equal(root.get("url"), url);
			}
		};
	}
}
