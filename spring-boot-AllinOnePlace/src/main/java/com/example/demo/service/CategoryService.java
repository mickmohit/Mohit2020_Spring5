package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.Category;

public interface CategoryService {

	List<Category> list();
	
	Page<Category> findAll(Pageable pageable);
	
	Page<Category> findAll(Long id, Pageable pageable);
	
	Category findOne(Long id);
	
	String add(Category category);
	
	String delete(Long id);
	
}
