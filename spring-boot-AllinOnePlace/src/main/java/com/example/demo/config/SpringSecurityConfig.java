package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	 * @Autowired private AuthenticationEntryPoint entryPoint;
	 */
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception {
	 * 
	 * auth.inMemoryAuthentication().withUser("user").password("{noop}password")
	 * .roles("USER"); }
	 */
	 
	@Override
	  protected void configure(AuthenticationManagerBuilder auth)
			  throws Exception {
	 
	  auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	  }

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/webjars/**").permitAll()
		.anyRequest().authenticated().and()
		.formLogin().loginPage("/user/login").permitAll().and()
		.logout().deleteCookies("remember-me").permitAll().and()
		.rememberMe().tokenValiditySeconds(120);
		//.httpBasic().authenticationEntryPoint(entryPoint); 
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
