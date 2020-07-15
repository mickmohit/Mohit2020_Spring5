package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.Video;

public interface VideoService {

	List<Video> list();
	
	Page<Video> findAll(Pageable pageable);
	
	Page<Video> findAll(Long id, Pageable pageable);
	
	Video findOne(Long id);
	
	String add(Video video);
	
	String delete(Long id);
}
