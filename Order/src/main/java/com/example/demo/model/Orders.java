package com.example.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Orders {

	@Id
	private Long id;
	private String customer_name;
	private Date order_date;
	private String shipping_address;
	
	
	  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	  
	  @JsonIgnoreProperties("order")
	private List<OrderItem> order_items;
	
	private int total;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public String getShipping_address() {
		return shipping_address;
	}
	public void setShipping_address(String shipping_address) {
		this.shipping_address = shipping_address;
	}
	
	
	
	public List<OrderItem> getOrder_items() {
		return order_items;
	}
	public void setOrder_items(List<OrderItem> order_items) {
		this.order_items = order_items;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", customer_name=" + customer_name + ", order_date=" + order_date
				+ ", shipping_address=" + shipping_address + ", order_items=" + order_items + ", total=" + total + "]";
	}
	
	
	
	
}
