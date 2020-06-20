package com.example.demo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "role")
@javax.persistence.Table(name = "`role`")//Manual escaping using the Hibernate-specific backtick character
public class Role {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long roleId;
	private String name;
	
	@OneToMany(targetEntity = User.class, mappedBy = "role", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<User> users;
	
	private transient long user_Id;//for Json ajax call from UI
	
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(long user_Id) {
		this.user_Id = user_Id;
	}
	
	
	
}
