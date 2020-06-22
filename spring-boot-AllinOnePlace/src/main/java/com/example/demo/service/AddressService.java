package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.Address;

public interface AddressService {

	List<Address> addressList();
	
	Page<Address> findAllAddress(Pageable pagebale);// for retrieving page & records
	
	Optional<Address> fineOne(Long id);
	
	String addAddress(Address address);
	
	String delete(Long id);
	
	void refershCache();
}
