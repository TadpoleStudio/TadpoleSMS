package com.tadpole.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tadpole.entity.Address;
import com.tadpole.repository.AddressRepository;
import com.tadpole.service.AddressService;

@Service("AddressService")
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepository addressRepository;

	public Address saveOrUpdateAddress(Address address) {

		return addressRepository.saveAndFlush(address);
	}

}
