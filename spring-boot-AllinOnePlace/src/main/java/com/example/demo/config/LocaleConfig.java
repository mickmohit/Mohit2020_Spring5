package com.example.demo.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class LocaleConfig implements WebMvcConfigurer { // instead of WebMvcConfigurerAdapter(it is depreciated from Spring 5 onwards) use WebMvcConfigurer 

	@Bean
	   public LocaleResolver localeResolver(){
	        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
	        localeResolver.setDefaultLocale(Locale.US);
	        return  localeResolver;
	    }

	    @Bean
	    public LocaleChangeInterceptor localeChangeInterceptor() {
	        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
	        localeChangeInterceptor.setParamName("lang");
	        return localeChangeInterceptor;
	    }

	    @Override
	    public void addInterceptors(InterceptorRegistry registry){
	        registry.addInterceptor(localeChangeInterceptor()).addPathPatterns("/*");
	    }
	
	@Bean
	public MessageSource messageSource()
	{
		ReloadableResourceBundleMessageSource   messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:locale/messages");
		messageSource.setFallbackToSystemLocale(false);
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setCacheSeconds(3600); //refresh once per hour
		return messageSource;
	}
}
