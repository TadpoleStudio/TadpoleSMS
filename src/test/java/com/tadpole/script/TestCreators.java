package com.tadpole.script;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tadpole.creator.StrutsConfigurationCreator;
import com.tadpole.entity.TadFunction;
import com.tadpole.repository.DropDownRepository;
import com.tadpole.repository.MenuRepository;
import com.tadpole.repository.TadFunctionRepository;

/**
 * This class is for basic data preparation for the web app starting up. using this class without retrieving data from the UI. The data must not be
 * changed.
 * 
 * @author jjiang
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestCreators {

	@Autowired
	private DropDownRepository dropDownRepository;

	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private TadFunctionRepository tadFunctionRepository;

	@Test
	public void testCreateStrutsConfiguration() {

		TadFunction tadFunction = tadFunctionRepository.findByEntityName("Menu");
		
		String code = StrutsConfigurationCreator.generateSourceFile(tadFunction);
		
		System.out.println(code);

	}
}
