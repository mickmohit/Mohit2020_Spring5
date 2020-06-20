package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Address> addressList() {
		// TODO Auto-generated method stub
		return addressRepository.findAll();
	}

	@Override
	public Optional<Address> fineOne(Long id) {
		// TODO Auto-generated method stub
		return addressRepository.findById(id);
	}

	@Override
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
	
	/*@Override
	public String delete(Long id) {
		// TODO Auto-generated method stub
		 addressRepository.deleteById(id);
		 return "Address Deleted Sucessfully";
	}*/
	
}
