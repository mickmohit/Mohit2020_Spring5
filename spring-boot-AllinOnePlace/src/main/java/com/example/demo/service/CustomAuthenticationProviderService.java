package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class CustomAuthenticationProviderService implements AuthenticationProvider {

	@Autowired
	private UserRepository userRepository; 
	
	@Override //AuthenticationException provide custom authentication process instead of UserDetailsService
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		UsernamePasswordAuthenticationToken authenticationToken=null;
		
		String userName=authentication.getName();
		String password=authentication.getCredentials().toString();
		
		User user= userRepository.findByUserName(userName);
		if(user!=null)
		{
			/*
			 * here comparing username and password manually which is hidden from us in UserDetailsService impl
			 */
			if(userName.equals(user.getUserName()) && BCrypt.checkpw(password, user.getPassword()))
			{
				Collection<GrantedAuthority> grantedAuthority=getGrantedAuthuroties(user);
				
				/*
				 * here setting up the user details like name,password,authority for Spring
				 * Security context
				 */	
				authenticationToken= new UsernamePasswordAuthenticationToken(
						new org.springframework.security.core.userdetails.User(userName, password, grantedAuthority), password, grantedAuthority);
			}
		}
		else
		{
			throw new UsernameNotFoundException("UserName "+userName+" Not Found");
		}
		return authenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	public Collection<GrantedAuthority> getGrantedAuthuroties(User user)
	{
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		
		if(user.getRole().getName().equals("admin"))
		{
			 grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		return grantedAuthorities;
	}

}
