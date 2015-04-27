package com.tadpole.service;

import org.springframework.data.domain.Page;

import com.tadpole.entity.Customer;
import com.tadpole.vo.search.CustomerSearchVo;

public interface CustomerService {

	Customer saveOrUpdateCustomer(Customer customer);

	void deleteCustomer(String customerId);
	
	Page<Customer> loadCustomers(String currentIndex, CustomerSearchVo customerSearchVo);
}
