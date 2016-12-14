package com.spring.shoppingCart.dao;

import com.spring.shoppingCart.model.Customer;

public interface CustomerDAO {

	public String addCustomer(Customer c);

	public Customer getCustomerByName(String username);

	public boolean checklogin(String username, String password);
}
