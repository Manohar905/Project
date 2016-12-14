package com.spring.shoppingCart.model;

public class Cart {
	private String cart_id;
	private String username;
	private String code_list;
	private String quantity_list;

	public String getCart_id() {
		return cart_id;
	}

	public void setCart_id(String cart_id) {
		this.cart_id = cart_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCode_list() {
		return code_list;
	}

	public void setCode_list(String code_list) {
		this.code_list = code_list;
	}

	public String getQuantity_list() {
		return quantity_list;
	}

	public void setQuantity_list(String quantity_list) {
		this.quantity_list = quantity_list;
	}
}
