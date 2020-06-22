package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.model.Address;


public interface AddressRepository extends JpaRepository<Address, Long> ,PagingAndSortingRepository<Address, Long>{

	@Query("FROM Address")
	List<Address> addressList();
}
