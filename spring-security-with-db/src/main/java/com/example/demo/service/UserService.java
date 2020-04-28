package com.example.demo.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.entity.Users;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UsersRepository;

@Service
public class UserService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public Optional<Users> findUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    public Optional<Users> findUserByUserName(String userName) {
        return usersRepository.findByName(userName);
    }

    public Users saveUser(Users user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        if(userRole!=null) {
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        }
        return usersRepository.save(user);
    }
}
