package com.example.demo.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

//@Component //removing this registration as this will be handled through form login, this is useful for non-form based authentication
public class AuthenticationEntryPoint extends BasicAuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {

		 response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName());
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		PrintWriter pw= response.getWriter();
		pw.println("HTTP status 401 "+authException.getMessage());
	}

	@Override
	public void afterPropertiesSet() {
		// TODO Auto-generated method stub
		setRealmName("localhost");
		super.afterPropertiesSet();
	}

	
}
