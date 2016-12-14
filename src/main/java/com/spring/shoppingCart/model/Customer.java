package com.spring.shoppingCart.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class Customer {

	@NotEmpty(message = "Username is required")
	@Size(min = 4, max = 50, message = "Username should be atleast 4 charecters long")
	private String username;

	@NotEmpty(message = "Password is required")
	@Size(min = 4, message = "Password should be atleast 4 charecters long")
	private String password;

	@NotEmpty(message = "Cannot be Empty")
	private String firstname;

	@NotEmpty(message = "Cannot be Empty")
	private String lastname;

	@Email
	private String email_id;

	@NotEmpty(message = "Address is required")
	private String address;

	@Min(value = 1000, message = "Not a valid Pincode")
	private int pincode;

	@NotEmpty(message = "Cannot be Empty")
	private String contact;
	
	
	//getter and setters

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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
}
