package com.example.demo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.service.util.MethodUtils;

@ControllerAdvice
public class BookExceptionHandler extends ResponseEntityExceptionHandler {

	@Override // this method is for customization of HTTP method responses when error occurred
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		return new ResponseEntity<>(MethodUtils.prepareErrorJSON(status, ex), status);
	}
	
	@Override //this is to handle exceptions occured while rest request
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		return new ResponseEntity<>(MethodUtils.prepareErrorJSON(status, ex), status);
	}
}
