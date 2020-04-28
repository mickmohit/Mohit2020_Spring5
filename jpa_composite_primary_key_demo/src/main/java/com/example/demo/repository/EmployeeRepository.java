package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeeIdentity;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, EmployeeIdentity> {

	//Retrieving all employees of a particular company
	public List<Employee> findByEmployeeIdentityCompanyId(String companyId);
}
