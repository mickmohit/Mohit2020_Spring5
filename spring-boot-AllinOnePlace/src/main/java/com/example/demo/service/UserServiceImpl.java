package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service
@CacheConfig(cacheNames={"userCache"}) //instead putting cache name again and again , put it only once.
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	@Cacheable()
	//@Cacheable("userCache")
	public List<User> userList() {
		// TODO Auto-generated method stub
		return userRepository.userList();
	}
	

	@Override
	@Cacheable()
	public Page<User> findAllUserPages(Pageable pageable) {
		
	//	return userRepository.findAll(pageable); older way of pagination
		return userRepository.findAll(PageRequest.of(pageable.getPageNumber() - 1, 5));
	}

	@Override
	public Optional<User> findOne(Long id) {
		// TODO Auto-generated method stub
		/*User user = new User();
		 user.setUserId(id.toString());
		 Example<User> userExample = Example.of(user);
		return userRepository.findOne(userExample);*/
		return userRepository.findById(id);
	}

	@Override
	@CachePut(key = "#user")
	//@CachePut(value="userCache", key="#user")//to handle if you have added/update any enrty but you do not want to refresh it manually, rather it will added/refreshed automatically
	public String addUser(User user) {
		
		String message="";
		JSONObject jsonObject= new JSONObject();
		Long longValue=user.getUserId();
		if(longValue==null)
		{
			message=" added";
		}else {
		message=" updated";
		}
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		
		System.out.println("role-------------"+user);
		
		
		//user.setRole(roleRepository.findById(user.getRole().getRoleId()).orElse(null));
		user.setRole(roleRepository.findById(user.getRole_Id()).orElse(null)); //for JSON AJax call
		
		try {
			jsonObject.put("status", "success");
			jsonObject.put("title", message+" Confirmation");
			jsonObject.put("message", userRepository.save(user).getUserName()+message+" Successfully");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonObject.toString();
	}
	
	/*@Override //for form based normall add call
	public User addUser(User user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		user.setRole(roleRepository.findById(user.getRole().getRoleId()).orElse(null));
		return userRepository.save(user);
	}*/

	@Override
	@CacheEvict(allEntries = true)
	public String deleteUser(Long id) {
		JSONObject jsonObject= new JSONObject();
		
		try {
		 userRepository.deleteById(id);
		 jsonObject.put("message", "User Deleted Successfully");
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 return jsonObject.toString();
	}
	
	/*@Override
	public String deleteUser(Long id) {
		// TODO Auto-generated method stub
		 userRepository.deleteById(id);
		 return "User Deleted Successfully";
	}*/

	@Override
	public List<Role> roleList() {
		// TODO Auto-generated method stub
		return roleRepository.findAll();
	}

	@Override
	@CacheEvict(allEntries = true)
	//@CacheEvict(value="userCache", allEntries = true) //to create manual refresh request from UI
	public void refershCache() {
		// TODO Auto-generated method stub
		
	}



}
