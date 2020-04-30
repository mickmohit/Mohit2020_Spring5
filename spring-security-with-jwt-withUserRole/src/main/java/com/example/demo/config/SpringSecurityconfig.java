package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled = true)
public class SpringSecurityconfig extends WebSecurityConfigurerAdapter  {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			
			.authorizeRequests().antMatchers("/api/auth/**").permitAll()
			.anyRequest().authenticated()
			// make sure we use stateless session; session won't be used to
			// store user's state.
			.and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//  here we create a spring security custom filter and plug it in the filter chain to be invoked by FilterChainProxy in the order we want.	
//for Authorization, Spring Boot Security provides method level security, as well as URL level security and that, covers almost every kind of applications. But the authentication process varies from app to app. For example, in some cases, Http Basic Authentication is enough and sometimes Form-based Authentication. Even in form-based authentication, sometimes a client is required to send AES encrypted password and if that is the case our authentication process changes. But we can deal with those variations by adding our custom Filter in the filter chain. 
//By default, spring has enabled BasicAuthenticationFilter and this is removed from the chain once we add formLogin() in the http. 
//by default filters are added in the FilterChainProxy in a spring security app in an order and of course, DelegatingFilterProxy invokes the FilterChainProxy class which in turn invokes the chain of filters in an order by sprin.
// below in place of jwtRequestFilter you can your own CustomLoginFilter that extends AbstractAuthenticationProcessingFilter and overrides it's abstract method attemptAuthentication(). In the implementation, you can have your custom logic to validate user credentials.		

//Add a filter to validate the tokens with every request,
		//syntax: public HttpSecurity addFilterBefore(Filter filter, Class beforeFilter)
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
}
