package com.example.demo.filter;

import java.io.IOException;

import javax.servlet.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


@Component
public class ActuatorFilter implements Filter {
	
	private static Logger log = LoggerFactory.getLogger(ActuatorFilter.class);
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("ActuatorFilter - doFilter");
		chain.doFilter(request, response);
	}
}
