package com.example.demo.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig { // this class is to call a specific filter when specific URL is requested else this filter wont be called during normal application work

	@Bean
	public FilterRegistrationBean<ActuatorFilter> registrationBean() {
		FilterRegistrationBean<ActuatorFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new ActuatorFilter());
		registrationBean.addUrlPatterns("/actuator/*");
		return registrationBean;
	}
}
