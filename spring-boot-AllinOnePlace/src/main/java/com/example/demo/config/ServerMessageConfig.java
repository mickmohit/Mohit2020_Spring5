package com.example.demo.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;

@Configuration
public class ServerMessageConfig {

	@Autowired
	private MessageSource messageSource;
	
	//below methods to get msgs from property file for server side msgs translation
	
	public String getMessage(String key)
	{
		Locale locale= LocaleContextHolder.getLocale();
		return messageSource.getMessage(key, null, locale);
	}
	
	public String getMessage(String key, String[] strArray)
	{
		Locale locale= LocaleContextHolder.getLocale();
		return messageSource.getMessage(key, strArray, locale);
	}
}
