package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


@Configuration
public class SpringSecurity extends WebSecurityConfigurerAdapter {
	
    @Override
    public void configure(HttpSecurity http) throws Exception {
    	
        http.antMatcher("/**").authorizeRequests()
            .antMatchers("/","/login**").permitAll()
            .anyRequest().authenticated()
            .and()
            .oauth2Login()
            .and().logout().invalidateHttpSession(true)
            .clearAuthentication(true).logoutSuccessUrl("/")
            .deleteCookies("JSESSIONID").permitAll().and()
            //.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
            .csrf().disable();
    }
}