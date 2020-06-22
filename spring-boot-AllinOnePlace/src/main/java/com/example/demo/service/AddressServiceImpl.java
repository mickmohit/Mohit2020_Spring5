package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Address;
import com.example.demo.model.User;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.UserRepository;

@Service
public class AddressServiceImpl implements AddressService{

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Cacheable("addressCache")
	public List<Address> addressList() {
		// TODO Auto-generated method stub
		return addressRepository.findAll(); //using JPA findall instead of addressList() from addressRepo
	}
	
	@Override
	@Cacheable("addressCache")
	public Page<Address> findAllAddress(Pageable pageable) {
		
		return addressRepository.findAll(PageRequest.of(pageable.getPageNumber() - 1, 5));
	}
	

	@Override
	public Optional<Address> fineOne(Long id) {
		// TODO Auto-generated method stub
		return addressRepository.findById(id);
	}

	@Override
	@CachePut(value="addressCache", key="#address")//to handle if you have added/update any enrty but you do not want to refresh it manually, rather it will added/refreshed automatically
	public String addAddress(Address address) {
		
		String message="";
		
		JSONObject jsonObject= new JSONObject();
		
		Long longValue=address.getAddressId();
		if(longValue==null)
		{
			message=" added";
		}
		message=" updated";
		
		//address.setUser(userRepository.findById(address.getUser().getUserId()).orElse(null));
		address.setUser(userRepository.findById(address.getUserId()).orElse(null)); //for JSOn ajax call
		
		try {
			jsonObject.put("status", "success");
			jsonObject.put("title", message+" Confirmation");
			jsonObject.put("message", addressRepository.save(address).getUser().getUserId()+message+" Successfully");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonObject.toString();
	}
	
	/*@Override
	public Address addAddress(Address address) {
		// TODO Auto-generated method stub
		System.out.println("-------------"+address);
		//User user=userRepository.findById(address.getUserId()).orElse(null); getUserId() for rest call
		address.setUser(userRepository.findById(address.getUser().getUserId()).orElse(null));
		return addressRepository.save(address);
	}*/

	@Override
	public String delete(Long id) {
	 JSONObject jsonObject= new JSONObject();
			
			try {
				addressRepository.deleteById(id);
			 jsonObject.put("message", "Address Deleted Successfully");
			}catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			 return jsonObject.toString();
	}

	@Override
	@CacheEvict(value="addressCache", allEntries = true)
	public void refershCache() {
		// TODO Auto-generated method stub
		
	}




	
	
	/*@Override
	public String delete(Long id) {
		// TODO Auto-generated method stub
		 addressRepository.deleteById(id);
		 return "Address Deleted Sucessfully";
	}*/
	
}
