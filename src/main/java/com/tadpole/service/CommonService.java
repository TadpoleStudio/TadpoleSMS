package com.tadpole.service;

import java.util.List;

import com.tadpole.entity.User;
import com.tadpole.vo.Option;


public interface CommonService {

	User getCurrentLoggedInUser();
	
	boolean checkUserNotHasRole(String username, String roleName);

	List<Option> findDatasourceByName(String dataSourceName);
	
}
