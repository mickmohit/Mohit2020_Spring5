package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user= userRepository.findByUserName(username);
		
		if(user==null)
		{
			throw new UsernameNotFoundException("User name "+username+" not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(),
				user.getPassword(), getGrantedAuthuroties(user));
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
