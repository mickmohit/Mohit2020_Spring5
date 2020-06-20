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

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class User {//extends AbstractPersistable<Long>{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	
	private String userName;
	private String password;
	
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
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + "]";
	}
	
	
	
}
