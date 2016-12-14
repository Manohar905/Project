package com.spring.shoppingCart.controller;

import java.util.List;

//import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
//import org.springframework.web.context.ServletContextAware;

import com.spring.shoppingCart.dao.ItemsDAO;
import com.spring.shoppingCart.dao.MycartDAO;
import com.spring.shoppingCart.model.Items;
import com.spring.shoppingCart.model.Login;
import com.spring.shoppingCart.model.Mycart;

@Controller
@SessionAttributes({ "mycartList", "cartcount", "totalamount", "nameslist", "clientsearch", "items_list", "selectedNames", "priceUpto",
		"categorieslist", "selectedCategories" })
public class ClientPageController {

	@Autowired
	private ItemsDAO itemsDAO;
	@Autowired
	private MycartDAO mycartDAO;

	String[] EMPTY_ARRAY = new String[0];

	@RequestMapping(value = "/clientpage", method = RequestMethod.POST, params = { "search" })
	public String searchCondition(@RequestParam("clientsearch") String clientsearch, @ModelAttribute("selectedNames") String[] itemnames,
			@ModelAttribute("selectedCategories") String[] itemcategories, @ModelAttribute("priceUpto") int priceUpto, Model model) {
		List<Items> items_list = itemsDAO.getConditionalItemsList(clientsearch,itemcategories, itemnames, priceUpto);
		if (items_list.size()==0) {
			model.addAttribute("search_error", "No matches found");
		} else {
			model.addAttribute("items_list", items_list);
			model.addAttribute("clientsearch", clientsearch);
		}
		return "clientpage";
	}

	@RequestMapping(value = "/clientpage", method = RequestMethod.POST, params = { "categoriescondition" })
	public String categoriesCondition(@RequestParam(value = "categoryname", required = false) String[] itemcategories,
			@ModelAttribute("clientsearch") String clientsearch, @ModelAttribute("selectedNames") String[] itemnames,
			@ModelAttribute("priceUpto") int priceUpto, Model model) {
		itemcategories = (itemcategories == null) ? (new String[0]) : itemcategories;
		model.addAttribute("selectedCategories", itemcategories);
		model.addAttribute("items_list", itemsDAO.getConditionalItemsList(clientsearch,itemcategories, itemnames, priceUpto));
		return "clientpage";
	}

	@RequestMapping(value = "/clientpage", method = RequestMethod.POST, params = { "namescondition" })
	public String namesCondition(@RequestParam(value = "itemname", required = false) String[] itemnames,
			@ModelAttribute("clientsearch") String clientsearch, @ModelAttribute("selectedCategories") String[] itemcategories,
			@ModelAttribute("priceUpto") int priceUpto, Model model) {
		itemnames = (itemnames == null) ? (new String[0]) : itemnames;
		model.addAttribute("selectedNames", itemnames);
		model.addAttribute("items_list", itemsDAO.getConditionalItemsList(clientsearch,itemcategories, itemnames, priceUpto));
		return "clientpage";
	}

	@RequestMapping(value = "/clientpage", method = RequestMethod.POST, params = { "pricecondition" })
	public String priceCondition(@RequestParam("priceUpto") int priceUpto, @ModelAttribute("clientsearch") String clientsearch,
			@ModelAttribute("selectedCategories") String[] itemcategories, @ModelAttribute("selectedNames") String[] itemnames, Model model) {
		model.addAttribute("priceUpto", priceUpto);
		model.addAttribute("items_list", itemsDAO.getConditionalItemsList(clientsearch,itemcategories, itemnames, priceUpto));
		return "clientpage";
	}

	@RequestMapping(value = "/clientpage", method = RequestMethod.POST, params = { "clearsearch" })
	public String clearsearch(Model model) {
		model.addAttribute("clientsearch", "");
		model.addAttribute("priceUpto", itemsDAO.getmaxPrice());
		model.addAttribute("selectedNames", EMPTY_ARRAY);
		model.addAttribute("selectedCategories", EMPTY_ARRAY);
		model.addAttribute("items_list", itemsDAO.getAllItemsList());
		return "clientpage";
	}

	@RequestMapping(value = "/clientpage", method = RequestMethod.POST, params = { "logout" })
	public String clientLogout(SessionStatus status, Model model) {
		status.setComplete();
		mycartDAO.clearMycart(); // clear present cart
		model.addAttribute("login", new Login());
		return "login";
	}

	@RequestMapping(value = "/clientpage", method = RequestMethod.POST, params = { "itemcode" })
	public String addToCart(@RequestParam("itemcode") String code, Model model) {
		String msg;
		Items item = itemsDAO.getItemDetails(code);
		Mycart mycart = new Mycart(item.getCode(), 1, item.getPrice());
		if (mycartDAO.addToMycart(mycart)) {
			msg = item.getCode() + " Added to cart";
		} else {
			msg = item.getCode() + " Already in cart";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("cartcount", mycartDAO.countMycart());
		return "clientpage";
	}

	@RequestMapping(value = "/clientpage", method = RequestMethod.POST, params = { "viewcart" })
	public String viewCart(Model model) {
		model.addAttribute("mycartList", mycartDAO.getMycart());
		model.addAttribute("totalamount", mycartDAO.totalMycart());
		return "viewcart";
	}
}
