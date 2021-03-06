package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		System.out.println("WebMvcConfigurer - addResourceHandlers() function get loaded...");
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");

		/*
		 * registry .addResourceHandler("/js/**") .addResourceLocations("/js/")
		 * .setCachePeriod(3600) .resourceChain(true);
		 */
     
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
			//if comment these too, then if you are setting modelandview in controller it will work
			// but for your security matchers and login success/fail url you need below mappings..
		  registry.addViewController("/home").setViewName("home");
		  registry.addViewController("/").setViewName("home");
		  registry.addViewController("/hello").setViewName("hello");
		  registry.addViewController("/login").setViewName("login");
		  registry.addViewController("/403").setViewName("403");
		  registry.addViewController("/signup").setViewName("signup");
		 
    }    
	
	@Bean
	 public InternalResourceViewResolver viewResolver() {
	  InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	  resolver.setPrefix("/WEB-INF/jsp/");
	  resolver.setSuffix(".jsp");
	  return resolver;
	 }    

	

	
}
