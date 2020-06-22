package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ManyToAny;

import com.example.demo.utils.ConstantUtils;
import com.example.demo.validator.Validate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Address {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long addressId;
	
	@NotNull
	//@Size(min=10, max=100)
	@Validate(min=10, max=100, regexp="", message="Please enter between {min}-{max} characters")
	private String addressLine;
	
	@NotNull
	@Size(min=3, max=15, message="Please enter between {min}-{max} characters")
	@Pattern(regexp=ConstantUtils.CHAR_PATTERN, message="Please enter only characters")
	private String city;
	
	@NotNull
	@Size(min=3, max=15, message="Please enter between {min}-{max} characters")
	@Pattern(regexp=ConstantUtils.CHAR_PATTERN, message="Please enter only characters")
	private String state;
	
	@NotNull
	@Size(min=3, max=15, message="Please enter between {min}-{max} characters")
	private String country;
	
	@NotNull
	@Size(min=6, max=6, message="Please enter atleast {min} digits")
	@Pattern(regexp=ConstantUtils.CODE_PATTERN, message="Please enter only digits")
	private String pinCode;
	
	//to update userid from address
	private transient Long userId; //make sure you use same variable name in your rest request
	
	
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;
		
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public long getAddressId() {
		return addressId;
	}
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
		
	public String getAddressLine() {
		return addressLine;
	}
	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", city=" + city + ", state=" + state + ", country=" + country
				+ ", user=" + user + "]";
	}
	
	
	
}
