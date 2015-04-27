package com.tadpole.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tadpole.entity.SmsTemplate;

public interface SmsTemplateRepository extends JpaRepository<SmsTemplate, Integer>, JpaSpecificationExecutor<SmsTemplate> {

}
