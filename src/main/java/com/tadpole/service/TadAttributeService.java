package com.tadpole.service;

import com.tadpole.entity.TadAttribute;


public interface TadAttributeService {

	TadAttribute saveOrUpdateTadAttribute(TadAttribute tadAttribute);

	void removeAttribute(String attributeId);

	void removeSingleFunction(String functionId);

}
