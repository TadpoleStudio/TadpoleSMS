package com.tadpole.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tadpole.entity.TadUser;
import com.tadpole.repository.TadUserRepository;
import com.tadpole.service.TadUserService;

@Service("TadUserService")
public class TadUserServiceImpl implements TadUserService {

	@Autowired
	TadUserRepository tadUserRepository;

	public void saveOrUpdateTadUser(TadUser tadUser) {

		tadUserRepository.save(tadUser);
	}

}
