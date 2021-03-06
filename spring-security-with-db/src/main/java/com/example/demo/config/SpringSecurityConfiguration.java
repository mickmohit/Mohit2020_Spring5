package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.UserDetailsService.UserDetailsServiceImpl;
import com.example.demo.repository.UsersRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //provides AOP security on methods, some of annotation it will enable are PreAuthorize PostAuthorize 
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
    private UserDetailsServiceImpl userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/* http.csrf().disable(); */
		/*http.authorizeRequests()
		.antMatchers("/secured/**").authenticated()
        .anyRequest().permitAll()
        .and()
        .formLogin().permitAll();*/
		
		 http.authorizeRequests()
		 .antMatchers("/").permitAll()
         .antMatchers("/login").permitAll()
         .antMatchers("/signup").permitAll()
        .antMatchers("**/hello/**").hasAuthority("ADMIN")
         .anyRequest().authenticated()
		  .and()
		    .formLogin().loginPage("/login")
		    .failureUrl("/login?error=true")
		    .defaultSuccessUrl("/hello")
		    .usernameParameter("username")
		    .passwordParameter("password")
		  .and()
		    .logout().logoutSuccessUrl("/login?logout") 
		    .and().rememberMe()
		   .and()
		   .exceptionHandling().accessDeniedPage("/403")
		   .and()
		    .csrf().disable();
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
	
	/*
	 * private PasswordEncoder getPasswordEncoder() { return new PasswordEncoder() {
	 * 
	 * @Override public String encode(CharSequence charSequence) { return
	 * charSequence.toString(); }
	 * 
	 * @Override public boolean matches(CharSequence charSequence, String s) {
	 * return true; } }; }
	 */
	 
	 @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	
}
