package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	@Query("FROM role r WHERE r.name=:name")
	Role findByName(@Param("name") String name);
}
