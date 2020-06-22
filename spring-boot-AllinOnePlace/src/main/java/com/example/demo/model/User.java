package com.example.demo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.example.demo.utils.ConstantUtils;

@Entity
public class User {//extends AbstractPersistable<Long>{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	
	@NotNull
	@Size(min=5, max=30, message="Please enter between {min}-{max} characters")
	@Pattern(regexp=ConstantUtils.CHAR_PATTERN, message="Please enter only characters")
	//@Validate(min=5, max=30, regexp=ConstantUtils.CHAR_PATTERN, message="Please enter between {min}-{max} characters and no digits")
	private String fullName;
	
	@NotNull
	private String userName;
	
	@NotNull
	private String password;
	
	@NotNull
	@Size(min=10, max=100, message="Please enter between {min}-{max} characters")
	@Pattern(regexp=ConstantUtils.EMAIL_PATTERN, message="Please enter only valid input")
	//@Validate(min=10, max=100, regexp=ConstantUtils.EMAIL_PATTERN, message="Please enter between {min}-{max} characters and valid input")
	private String email;
	
	@NotNull
	@Size(min=10, max=10, message="Please enter atleast {min} digits")
	@Pattern(regexp=ConstantUtils.MOBILE_PATTERN, message="Please enter only digits")
	//@Validate(min=10, max=10, regexp=ConstantUtils.MOBILE_PATTERN, message="Please enter atleast {min} digits and no characters")
	private String mobile;
	
	@OneToMany(targetEntity=Address.class, mappedBy="user", fetch=FetchType.LAZY , cascade = CascadeType.ALL)
	private Set<Address> addresses;
	
	@ManyToOne
	@JoinColumn(name="roleid")
	private Role role;
		
	private transient long role_Id; // for mapping UI json ajax request to form add 
	//without above variable json request come as User with no roleid [userId=0, userName=admin, password=**]	
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
		
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
		 
	public long getRole_Id() {
		return role_Id;
	}
	public void setRole_Id(long role_Id) {
		this.role_Id = role_Id;
	}
	
	
	
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + "]";
	}
	
	
	
}
