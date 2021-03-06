package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeeIdentity;
import com.example.demo.repository.EmployeeRepository;

@SpringBootApplication
public class JpaCompositePrimaryKeyDemoApplication implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;


	public static void main(String[] args) {
		SpringApplication.run(JpaCompositePrimaryKeyDemoApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		
		// Cleanup employees table
        employeeRepository.deleteAllInBatch();

        // Insert a new Employee in the database
        Employee employee = new Employee(new EmployeeIdentity("E-123", "D-457"),
                "Rajeev Kumar Singh",
                "rajeev@callicoder.com",
                "+91-9999999999");

        employeeRepository.save(employee);
		
     // Retrieving an Employee Record with the composite primary key
        Optional<Employee> employees = employeeRepository.findById(new EmployeeIdentity("E-123", "D-457"));
      employees.ifPresent(System.out::println);
        
     // Retrieving all the employees of a particular company
       List<Employee> employees1 = employeeRepository.findByEmployeeIdentityCompanyId("D-457");
       employees1.forEach(System.out::println);
	}

}
