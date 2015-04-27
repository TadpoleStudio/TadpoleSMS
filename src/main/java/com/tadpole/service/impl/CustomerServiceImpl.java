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

import com.tadpole.entity.Customer;
import com.tadpole.repository.CustomerRepository;
import com.tadpole.service.CustomerService;
import com.tadpole.vo.search.CustomerSearchVo;

@Service("CustomerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	public Customer saveOrUpdateCustomer(Customer customer) {

		return customerRepository.saveAndFlush(customer);
	}
	
	public void deleteCustomer(String customerId) {

		customerRepository.delete(Integer.valueOf(customerId));
	}
	
	public Page<Customer> loadCustomers(String currentIndex, CustomerSearchVo customerSearchVo) {
		Specifications<Customer> customerSpecification = null;
		if (StringUtils.isNotBlank(customerSearchVo.getName())) {
			
       	 	customerSpecification = Specifications.where(customerNameSpecification(customerSearchVo.getName()));
		}
		if (StringUtils.isNotBlank(customerSearchVo.getPhone())) {

         	if (customerSpecification == null) {
                customerSpecification = Specifications.where(customerPhoneSpecification(customerSearchVo.getPhone()));
         	} else {
                customerSpecification.and(customerPhoneSpecification(customerSearchVo.getPhone()));
        	}
 		}
		if (customerSearchVo.getBirthday() != null) {

         	if (customerSpecification == null) {
                customerSpecification = Specifications.where(customerBirthdaySpecification(customerSearchVo.getBirthday()));
         	} else {
                customerSpecification.and(customerBirthdaySpecification(customerSearchVo.getBirthday()));
        	}
 		}

		if (customerSpecification == null) {
       		return customerRepository.findAll(new PageRequest(Integer.valueOf(currentIndex) - 1, 10));
		} else {
        	return customerRepository.findAll(customerSpecification, new PageRequest(Integer.valueOf(currentIndex) - 1, 10));
		}		
		
	}

	private Specification<Customer> customerNameSpecification(final String name) {

		return new Specification<Customer>(){

			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				return cb.equal(root.get("name"), name);
			}
		};
	}
	private Specification<Customer> customerPhoneSpecification(final String phone) {

		return new Specification<Customer>(){

			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				return cb.equal(root.get("phone"), phone);
			}
		};
	}
	private Specification<Customer> customerBirthdaySpecification(final Date birthday) {

		return new Specification<Customer>(){

			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				return cb.equal(root.get("birthday"), birthday);
			}
		};
	}
}
