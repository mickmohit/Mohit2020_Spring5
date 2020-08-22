package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderItem {

	@Id
	private String product_code;
	private String product_name;
	private int quantity;
	
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "order_id", nullable = false) private Orders order;
	 */
	
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "OrderItem [product_code=" + product_code + ", product_name=" + product_name + ", quantity=" + quantity
				+ "]";
	}
	
	
	
	/*
	 * public Orders getOrder() { return order; } public void setOrder(Orders order)
	 * { this.order = order; }
	 */
	/*
	 * @Override public String toString() { return "OrderItem [product_code=" +
	 * product_code + ", product_name=" + product_name + ", quantity=" + quantity +
	 * ", order=" + order + "]"; }
	 */
	
	
	
}
