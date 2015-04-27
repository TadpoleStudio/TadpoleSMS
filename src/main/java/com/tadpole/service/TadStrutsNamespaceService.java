package com.tadpole.service;

import org.springframework.data.domain.Page;

import com.tadpole.entity.TadStrutsNamespace;


public interface TadStrutsNamespaceService {

	TadStrutsNamespace saveOrUpdateTadStrutsNamespace(TadStrutsNamespace tadStrutsNamespace);

	Page<TadStrutsNamespace> loadTadStrutsNamespaces();
}
