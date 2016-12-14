package com.spring.shoppingCart.model;

import org.hibernate.validator.constraints.NotEmpty;

public class Login {

	@NotEmpty(message="Username is empty")
	private String username;

	@NotEmpty(message="Password field is empty")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
