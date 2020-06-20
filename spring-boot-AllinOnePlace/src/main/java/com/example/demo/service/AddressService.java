package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Address;

public interface AddressService {

	List<Address> addressList();
	
	Optional<Address> fineOne(Long id);
	
	String addAddress(Address address);
	
	String delete(Long id);
}
