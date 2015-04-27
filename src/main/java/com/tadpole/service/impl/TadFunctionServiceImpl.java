package com.tadpole.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.tadpole.creator.ControllerCreator;
import com.tadpole.creator.EntityBeanCreator;
import com.tadpole.creator.JavaSearchVoCreator;
import com.tadpole.creator.JsObjectCreator;
import com.tadpole.creator.JsSearchObjectCreator;
import com.tadpole.creator.JspCreator;
import com.tadpole.creator.RepositoryCreator;
import com.tadpole.creator.RestWebServiceCreator;
import com.tadpole.creator.ServiceImplemetationCreator;
import com.tadpole.creator.ServiceInterfaceCreator;
import com.tadpole.creator.StrutsConfigurationCreator;
import com.tadpole.entity.Menu;
import com.tadpole.entity.TadAttribute;
import com.tadpole.entity.TadFunction;
import com.tadpole.repository.MenuRepository;
import com.tadpole.repository.TadAttributeRepository;
import com.tadpole.repository.TadFunctionRepository;
import com.tadpole.service.TadFunctionService;
import com.tadpole.vo.JpaAttributeDefinition;
import com.tadpole.vo.JpaEntityDefinition;
import com.tadpole.vo.JspVo;

@Service("TadFunctionService")
public class TadFunctionServiceImpl implements TadFunctionService {

	@Autowired
	TadFunctionRepository tadFunctionRepository;

	@Autowired
	TadAttributeRepository tadAttributeRepository;

	@Autowired
	MenuRepository menuRepository;

	@Resource(name = "dataSource")
	org.springframework.jdbc.datasource.DriverManagerDataSource dataSource;

	public TadFunction saveOrUpdateTadFunction(TadFunction tadFunction) {

		boolean isCreation = tadFunction.getId() == null;

		if (isCreation) {

			TadFunction functionWithSameTableName = tadFunctionRepository.findByTableName(tadFunction.getTableName());
			if (functionWithSameTableName != null) {
				throw new RuntimeException("The table exists.");
			}

			TadFunction functionWithSameEntityName = tadFunctionRepository.findByEntityName(tadFunction.getEntityName());
			if (functionWithSameEntityName != null) {
				throw new RuntimeException("The entity exists.");
			}

			TadFunction functionWithSameMenuTitle = tadFunctionRepository.findByMenuTitle(tadFunction.getMenuTitle());
			if (functionWithSameMenuTitle != null) {
				throw new RuntimeException("The menu title exists.");
			}
		}

		return tadFunctionRepository.saveAndFlush(tadFunction);
	}

	public TadFunction generateCode(String functionId, String codeType) {

		if (StringUtils.isBlank(functionId)) {
			throw new RuntimeException("Function id is not provided.");
		}

		Integer id = Integer.valueOf(functionId);
		TadFunction function = tadFunctionRepository.findOne(id);

		JpaEntityDefinition jpaEntityDefinition = new JpaEntityDefinition();
		jpaEntityDefinition.setTableName(function.getTableName());
		jpaEntityDefinition.setJavaClassName(function.getEntityName());
		jpaEntityDefinition.setRepositoryAdditionalCode(function.getRepositoryAdditionalCode());

		List<TadAttribute> tadAttributes = tadAttributeRepository.findByFunctionId(functionId);
		List<JpaAttributeDefinition> jpaAttributeDefinitions = new ArrayList<JpaAttributeDefinition>();

		for (TadAttribute tadAttribute : tadAttributes) {
			JpaAttributeDefinition jpaAttributeDefinition = new JpaAttributeDefinition(tadAttribute.getName(), tadAttribute.getType(), tadAttribute.getSearchable());
			jpaAttributeDefinition.setTextLong(tadAttribute.getTextLong());
			List<String> columnAttributes = Lists.newArrayList();
			if (tadAttribute.getIsUnique()) {
				
				columnAttributes.add("unique = true");
			}
			if (tadAttribute.getRequired()) {
				
				columnAttributes.add("nullable = false");
			}
			
			if (!columnAttributes.isEmpty()) {
				jpaAttributeDefinition.setColumnAttributes(Joiner.on(",").join(columnAttributes));
			}
			
			jpaAttributeDefinitions.add(jpaAttributeDefinition);
		}
		jpaEntityDefinition.setAttributeDefinitions(jpaAttributeDefinitions);

		if (codeType.equals("rest")) {
			
			generateRestCode(function, jpaEntityDefinition);
			
		} else {
			
			String jpaEntityCode = EntityBeanCreator.generateSourceFile(jpaEntityDefinition);
			function.setJpaEntityCode(jpaEntityCode);
			function.setJpaEntityFilePath(EntityBeanCreator.getSourceFileName(jpaEntityDefinition));

			String jsVoCode = JsObjectCreator.generateSourceFile(jpaEntityDefinition);
			function.setJsVoCode(jsVoCode);
			function.setJsVoFilePath(JsObjectCreator.getSourceFileName(jpaEntityDefinition));

			String jsSearchVoCode = JsSearchObjectCreator.generateSourceFile(jpaEntityDefinition);
			String jsSearchVoFilePath = JsSearchObjectCreator.getSourceFileName(jpaEntityDefinition);
			function.setJsSearchVoCode(jsSearchVoCode);
			function.setJsSearchVoFilePath(jsSearchVoFilePath);

			String javaSearchVoCode = JavaSearchVoCreator.generateSourceFile(jpaEntityDefinition);
			String javaSearchVoFilePath = JavaSearchVoCreator.getSourceFileName(jpaEntityDefinition);
			function.setJavaSearchVoCode(javaSearchVoCode);
			function.setJavaSearchVoFilePath(javaSearchVoFilePath);

			String repositoryCode = RepositoryCreator.generateSourceFile(jpaEntityDefinition);
			function.setRepositoryCode(repositoryCode);
			function.setRepositoryFilePath(RepositoryCreator.getSourceFileName(jpaEntityDefinition));

			String serviceInterfaceCode = ServiceInterfaceCreator.generateSourceFile(jpaEntityDefinition);
			function.setServiceInterfaceCode(serviceInterfaceCode);
			function.setServiceInterfaceFilePath(ServiceInterfaceCreator.getSourceFileName(jpaEntityDefinition));

			String serviceImplementationCode = ServiceImplemetationCreator.generateSourceFile(jpaEntityDefinition);
			function.setServiceImplementationCode(serviceImplementationCode);
			function.setServiceImplementationFilePath(ServiceImplemetationCreator.getSourceFileName(jpaEntityDefinition));

			String actionCode = ControllerCreator.generateSourceFile(jpaEntityDefinition);
			function.setActionCode(actionCode);
			function.setActionFilePath(ControllerCreator.getSourceFileName(jpaEntityDefinition));

			String strutsConfiguration = StrutsConfigurationCreator.generateSourceFile(function);
			function.setStrutsConfigurationgCode(strutsConfiguration);
			function.setStrutsConfigurationgFilePath(StrutsConfigurationCreator.getSourceFileName(function));

			JspVo jspVo = generateJspVo(jpaEntityDefinition, function, tadAttributes);

			String jspCode = JspCreator.generateSourceFile(jspVo);
			function.setJspCode(jspCode);
			function.setJspFilePath(JspCreator.getSourceFileName(jspVo));
			
			generateRestCode(function, jpaEntityDefinition);
		}

		TadFunction result = tadFunctionRepository.saveAndFlush(function);

		if (result != null) {

			createOrUpdateMenuForFunction(function);
		}

		return result;
	}
	
	private void generateRestCode(TadFunction function, JpaEntityDefinition jpaEntityDefinition) {
		
		String restCode = RestWebServiceCreator.generateSourceFile(jpaEntityDefinition);
		String restFilePath = RestWebServiceCreator.getSourceFileName(jpaEntityDefinition);
		function.setRestCode(restCode);
		function.setRestFilePath(restFilePath);
		
	}

	private JspVo generateJspVo(JpaEntityDefinition jpaEntityDefinition, TadFunction function, List<TadAttribute> tadAttributes) {

		JspVo jspVo = new JspVo();
		
		jspVo.setFirstLetterLowerCaseJavaClassName(jpaEntityDefinition.getFirstLetterLowerCaseJavaClassName());
		jspVo.setAttributeDefinitions(jpaEntityDefinition.getAttributeDefinitions());
		jspVo.setJavaClassName(jpaEntityDefinition.getJavaClassName());
		jspVo.setStrutsNamespace(function.getStrutsNamespace());
		jspVo.setTitle(function.getTitle());
		jspVo.setTadAttributes(tadAttributes);
		List<TadAttribute> searchableTadAttributes = Lists.newArrayList();
		for (TadAttribute tadAttribute : tadAttributes) {
			if (tadAttribute.getSearchable() != null && tadAttribute.getSearchable()) {
				searchableTadAttributes.add(tadAttribute);
			}
		}

		Set<String> datasourceNames = Sets.newHashSet();
		for (TadAttribute tadAttribute : tadAttributes) {

			if (StringUtils.isNotBlank(tadAttribute.getDataSourceName())) {
				datasourceNames.add(tadAttribute.getDataSourceName());
			}
			
			if (StringUtils.isNotBlank(tadAttribute.getSearchTextDataSourceName())) {
				datasourceNames.add(tadAttribute.getSearchTextDataSourceName());
			}
			
			
		}

		jspVo.setDatasourceNames(datasourceNames);

		jspVo.setSearchableTadAttributes(searchableTadAttributes);
		return jspVo;
	}

	private void createOrUpdateMenuForFunction(TadFunction function) {

		Integer menuId = function.getMenuId();
		Menu menu = null;
		String elementId = function.getEntityName() + "Link";
		String link = "/TadpoleSMS/" + function.getStrutsNamespace() + "/load" + function.getEntityName() + "Mgr.action";
		String title = function.getMenuTitle();

		if (menuId == null) {

			menu = new Menu(title, link, elementId);
			menu.setVisible(true);

			Menu savedMenu = menuRepository.saveAndFlush(menu);

			function.setMenuId(savedMenu.getId());

			tadFunctionRepository.saveAndFlush(function);

		} else {
			menu = menuRepository.findOne(menuId);

			if (menu == null) {
				throw new RuntimeException("WTF!");
			}

			menu.setElementId(elementId);
			menu.setUrl(link);
			menu.setTitle(title);

			menuRepository.saveAndFlush(menu);
		}

	}

	public List<String> loadAllTableNames() {

		return tadFunctionRepository.loadAllTableNames();
	}

	public TadFunction loadSingleFunction(String tableNameSearch) {

		return tadFunctionRepository.findByTableName(tableNameSearch);
	}

	public List<TadAttribute> loadFunctionAttrites(String functionId) {

		return tadAttributeRepository.findByFunctionId(functionId);
	}

}
