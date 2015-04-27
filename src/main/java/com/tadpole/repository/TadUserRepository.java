package com.tadpole.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tadpole.entity.TadUser;

public interface TadUserRepository extends JpaRepository<TadUser, Integer>, JpaSpecificationExecutor<TadUser> {

	// public TadUser findByName(String name);
}
