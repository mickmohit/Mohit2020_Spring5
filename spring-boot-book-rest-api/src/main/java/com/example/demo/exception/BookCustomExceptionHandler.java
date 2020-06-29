package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.service.util.MethodUtils;

@ControllerAdvice // this is to throw normal bookNotFoundException  controller advice
public class BookCustomExceptionHandler {

	@ExceptionHandler(value = BookNotFoundException.class)
	public ResponseEntity<String> bookNotFoundException(BookNotFoundException exception) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(MethodUtils.prepareErrorJSON(status, exception), status);
	}
	
}
