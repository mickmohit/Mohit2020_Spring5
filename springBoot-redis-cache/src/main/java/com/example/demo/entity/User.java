package com.example.demo.entity;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

//@RedisHash("User")//RedisHash marks Objects as aggregate roots to be stored in a Redis hash
public class User implements Serializable {

	private String name;
	private int age;
	private String id;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User() {}
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", id=" + id + "]";
	}
	
	
}
