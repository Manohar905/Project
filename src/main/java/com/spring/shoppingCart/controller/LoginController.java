package com.spring.shoppingCart.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.shoppingCart.dao.CustomerDAO;
import com.spring.shoppingCart.dao.ItemsDAO;
import com.spring.shoppingCart.dao.MycartDAO;
import com.spring.shoppingCart.model.Customer;
import com.spring.shoppingCart.model.Items;
import com.spring.shoppingCart.model.ItemsImage;
import com.spring.shoppingCart.model.Login;

@Controller
@SessionAttributes({ "login", "customer", "name", "nameslist", "clientsearch", "items_list", "selectedNames", "cartcount", "maxPrice", "priceUpto",
		"categorieslist","selectedCategories" })
public class LoginController {

	@Autowired
	private CustomerDAO customerDAO;
	@Autowired
	private ItemsDAO itemsDAO;
	@Autowired
	private MycartDAO mycartDAO;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("login", new Login());
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, params = { "signin" })
	public String login(@Valid Login login, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "login";
		}

		if (customerDAO.checklogin(login.getUsername(), login.getPassword())) {
			if (customerDAO.getCustomerByName(login.getUsername()).getFirstname().equals("Adminstrator")) {
				model.addAttribute("name", login.getUsername());
				model.addAttribute("itemsImage", new ItemsImage());
				model.addAttribute("items", new Items());
				model.addAttribute("categorieslist", itemsDAO.getAllCategoriesList());
				model.addAttribute("defaultTab_Selector", "#tabs-1");
				return "adminpage";
			} else {
				model.addAttribute("name", login.getUsername());
				model.addAttribute("cartcount", 0);
				model.addAttribute("maxPrice", itemsDAO.getmaxPrice());
				model.addAttribute("priceUpto", itemsDAO.getmaxPrice());
				model.addAttribute("clientsearch", "");
				model.addAttribute("nameslist", itemsDAO.getAllNamesList());
				model.addAttribute("categorieslist", itemsDAO.getAllCategoriesList());
				model.addAttribute("selectedNames", new String[0]);
				model.addAttribute("selectedCategories", new String[0]);
				model.addAttribute("items_list", itemsDAO.getAllItemsList());				
				mycartDAO.clearMycart(); // clear previous cart
				return "clientpage";
			}
		} else {
			model.addAttribute("error", "invalid User Info");
			return "login";
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, params = { "register" })
	public String register(Model model) {
		model.addAttribute("customer", new Customer());
		return "customer";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String toAdminPage(Model model) {
		model.addAttribute("name", "admin");
		model.addAttribute("itemsImage", new ItemsImage());
		model.addAttribute("items", new Items());
		return "adminpage";
	}
}
