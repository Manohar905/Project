package com.spring.shoppingCart.controller;

import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.spring.shoppingCart.dao.MycartDAO;
import com.spring.shoppingCart.model.Login;
import com.spring.shoppingCart.model.Mycart;

@Controller
@SessionAttributes({ "cartcount", "mycartList", "totalmount" })
public class ViewcartController {

	@Autowired
	private MycartDAO mycartDAO;

	@RequestMapping(value = "/viewcart", method = RequestMethod.POST, params = { "logout" })
	public String clientLogout(SessionStatus status, Model model) {
		status.setComplete();
		mycartDAO.clearMycart(); // clear present cart
		model.addAttribute("login", new Login());
		return "login";
	}

	@RequestMapping(value = "/continue_shopping", method = RequestMethod.POST, params = { "continueShopping" })
	public String continueShopping(Model model) {
		model.addAttribute("cartcount", mycartDAO.countMycart());
		return "clientpage";
	}

	@RequestMapping(value = "/modify_cart", method = RequestMethod.POST, params = { "edit" })
	public String edititem(
			@ModelAttribute("mycartList") List<Mycart> mycartlist,
			@RequestParam("edit") String edit,
			@RequestParam("quantity") int[] quantity, Model model) {
		int i = 0;
		do {
			if (edit.equals(mycartlist.get(i).getCodes())) {
				break;
			}
			i++;
		} while (i < mycartlist.size());
		mycartDAO.editQuantity(edit, quantity[i]);
		model.addAttribute("msg", edit + " is updated");
		model.addAttribute("mycartList", mycartDAO.getMycart());
		model.addAttribute("totalamount", mycartDAO.totalMycart());
		model.addAttribute("cartcount", mycartDAO.countMycart());
		return "viewcart";
	}

	@RequestMapping(value = "/modify_cart", method = RequestMethod.POST, params = { "delete" })
	public String deleteitem(@RequestParam("delete") String delete_item,
			Model model) {
		mycartDAO.deleteFromMycart(delete_item);
		model.addAttribute("mycartList", mycartDAO.getMycart());
		model.addAttribute("totalamount", mycartDAO.totalMycart());
		model.addAttribute("msg", delete_item + " delete is selected");
		return "viewcart";
	}

	@RequestMapping(value = "/modify_cart", method = RequestMethod.POST, params = { "checkout" })
	public String checkout(SessionStatus status, Model model) {
		status.setComplete();
		mycartDAO.clearMycart(); // clear present cart
		JOptionPane.showMessageDialog(null, "Thankyou for shopping");
		model.addAttribute("login", new Login());
		return "login";
	}
}
