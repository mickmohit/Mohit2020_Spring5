package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.User;

public interface AmazonService {

	String uploadFile(MultipartFile multipartFile, User user);
}
