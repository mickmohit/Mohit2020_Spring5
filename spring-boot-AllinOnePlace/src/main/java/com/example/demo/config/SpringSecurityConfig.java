package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.example.demo.service.CustomAuthenticationProviderService;
import com.example.demo.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	/* this is for non form based authentication like brower pop-up **********No form login way**************
	 * @Autowired private AuthenticationEntryPoint entryPoint;
	 
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception {
	 * 
	 * auth.inMemoryAuthentication().withUser("user").password("{noop}password")
	 * .roles("USER"); }
	 */
	
	/*@Autowired  // **********Way-1************** this is for directly using custom UserDetailsService impl. to retreive user details
	private UserDetailsServiceImpl userDetailsService;
	 
	@Override
	  protected void configure(AuthenticationManagerBuilder auth)
			  throws Exception {
	 
	  auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	  }*/
	
	//**********Way-2**************The  UserDetailsService will fetch the User object from the database and hand over to Spring Security as a  UserDetails object. In the method, we instantiated the  DaoAuthenticationProvider
	//and initialized it with the  PasswordEncoder and  UserDetailsService implementations.
	//We will also auto wire in the  AuthenticationManagerBuilder. Spring Security will use this to set up the  AuthenticationProvider.
	/*@Autowired
	private AuthenticationProvider authenticationProvider; 
	
	@Autowired
	public void configureAuthManager(AuthenticationManagerBuilder authenticationManagerBuilder) {
		authenticationManagerBuilder.authenticationProvider(authenticationProvider);
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider(PasswordEncoder passwordEncoder,
			UserDetailsService userDetailsService)
	{
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		return daoAuthenticationProvider;
	}*/
	
	// Custom Authentication Provider | Spring Security  **********Way-3**************
	/*
	 * The standard and most common implementation is the DaoAuthenticationProvider
	 * – which retrieves the user details from a simple, read-only user DAO – the
	 * UserDetailsService. This User Details Service only has access to the username
	 * in order to retrieve the full user entity. This is enough for most scenarios.
	 * More custom scenarios will still need to access the full Authentication
	 * request to be able to perform the authentication process. For example, when
	 * authenticating against some external, third party service (such as Crowd) –
	 * both the username and the password from the authentication request will be
	 * necessary. For these, more advanced scenarios, we'll need to define a custom
	 * Authentication Provider
	 */	@Autowired
	private CustomAuthenticationProviderService customAuthenticationProviderService;  
	
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.authenticationProvider(customAuthenticationProviderService);
	}

	
	
	@Override
	public void configure(WebSecurity web) throws Exception { //for ignoring static stuff from application 
		// TODO Auto-generated method stub
		web.ignoring().antMatchers("/webjars/**", "/js/**", "/css/**");
	}



	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests()//.antMatchers("/webjars/**").permitAll()
		.antMatchers("/user/register", "/user/add").permitAll()
		.anyRequest().authenticated().and()
		.formLogin().loginPage("/user/login")
		.successHandler(loginSuccessHandler()) //customize Login Handlers
		.failureHandler(loginFailureHandler()) //customize Login Handlers
		 .permitAll().and()
		.logout()
		.logoutSuccessHandler(logoutSuccessHandler()) //customize Login Handlers
		.deleteCookies("remember-me").permitAll().and()
		.rememberMe().tokenValiditySeconds(120);
		//.httpBasic().authenticationEntryPoint(entryPoint); 
	}
	
	@Autowired
	private Environment env;
	
	public AuthenticationSuccessHandler loginSuccessHandler()
	{
		return (request,response,authentication) -> response.sendRedirect(env.getProperty("server.servlet.context-path"));
	}
	
	private AuthenticationFailureHandler loginFailureHandler() {
		
		return (request,response,exception) -> {
			request.getSession().setAttribute("error", "Username or passowrd is invalid"); //setting session msg attribute here in spring security instead of handling in controller class
			response.sendRedirect(env.getProperty("server.servlet.context-path")+ "/user/login");
			};
		}
	
	
	private LogoutSuccessHandler logoutSuccessHandler() {
		
		return (request,response,authentication) -> {
			request.getSession().setAttribute("message", "User has been Logged out");//setting session msg attribute here in spring security instead of handling in controller class
			response.sendRedirect(env.getProperty("server.servlet.context-path")+ "/user/login");
			};
		}




	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	
	
}
