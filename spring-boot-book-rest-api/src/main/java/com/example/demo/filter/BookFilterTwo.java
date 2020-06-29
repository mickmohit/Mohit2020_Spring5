package com.example.demo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class BookFilterTwo implements Filter{
	
	private Logger log= LoggerFactory.getLogger(BookFilterTwo.class);
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("BookFilterTwo Calling - doFilter");	
		chain.doFilter(request, response);
		log.info("Mohit BookFilter two inside method calling" );
		/*
		 * log.info("Local Port - " + request.getLocalPort()); log.info("Server Name - "
		 * + request.getServerName()); log.info("Protocol - " + request.getProtocol());
		 * HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		 * log.info("Method - " + httpServletRequest.getMethod());
		 * log.info("Request URI - " + httpServletRequest.getRequestURI());
		 * log.info("Servlet Path - " + httpServletRequest.getServletPath());
		 */
	}

}
