package com.spring.shoppingCart.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.shoppingCart.dao.CustomerDAO;
import com.spring.shoppingCart.model.Customer;
import com.spring.shoppingCart.model.Login;

@Controller
public class CustomerController {

	@Autowired
	private CustomerDAO customerDAO;

	@RequestMapping(value = "/customer", method = RequestMethod.POST, params = { "cancel" })
	public String login(Model model) {
		model.addAttribute("login", new Login());
		return "login";
	}
	
	@RequestMapping(value = "/customer", method = RequestMethod.POST, params = { "clear" })
	public String clear(Model model) {
		model.addAttribute("customer", new Customer());
		return "customer";
	}

	@RequestMapping(value = "/customer", method = RequestMethod.POST, params = { "register" })
	public String register(@Valid Customer customer,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "customer";
		}
		String temp = customerDAO.addCustomer(customer);
		if (temp.equals("sucess")) {
			model.addAttribute("msg", customer.getUsername()
					+ " - registerd succesfully");
			model.addAttribute("customer", new Customer());
			return "customer";
		} else if (temp.equals("failure")){
			model.addAttribute("msg", "Unsuccessful registration");
			return "customer";
		}else{
			model.addAttribute("msg", temp);
			return "customer";
		}
	}
}
