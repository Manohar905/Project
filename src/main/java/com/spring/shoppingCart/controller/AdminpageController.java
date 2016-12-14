package com.spring.shoppingCart.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.shoppingCart.dao.ItemsDAO;
import com.spring.shoppingCart.model.Items;
import com.spring.shoppingCart.model.ItemsImage;
import com.spring.shoppingCart.model.Login;

@Controller
@SessionAttributes({ "categorieslist" })
public class AdminpageController {

	private String imagefolder = "/home/kommineni/Documents/workspace-sts-3.6.3.SR1/onlineShopping/src/main/webapp/resources/";

	@Autowired
	private ItemsDAO itemsDAO;

	@RequestMapping(value = "/adminpage_logout", method = RequestMethod.POST, params = { "logout" })
	public String for_logout(Model model) {
		model.addAttribute("login", new Login());
		return "login";
	}

	@RequestMapping(value = "/adminpage_additem", method = RequestMethod.POST, params = { "additem" })
	public String additems(@RequestParam("newCat") String newcat,@Valid ItemsImage itemsImage, BindingResult bindingResult, Model model) {
		String msg = "";
		if(itemsImage.getCategory().equals("--- Select Category ---")){
			if(newcat.equals("")){
				model.addAttribute("msg", "Choose/Enter a Category");
				return "adminpage";
			}
			itemsImage.setCategory(newcat);
		}
		if (!bindingResult.hasErrors()) {
			try {
				if (itemsImage.getImage().isEmpty()) {
					msg = "Empty file selected";
				} else if (!itemsImage.getImage().getContentType().equals("image/jpeg")) {
					msg = "Image format should be JPG";
				} else {
					File file = new File(imagefolder + itemsImage.getImage().getOriginalFilename());
					FileUtils.writeByteArrayToFile(file, itemsImage.getImage().getBytes());
					String temp = itemsDAO.addItems(itemsImage);
					if (temp.equals("sucess")) {
						model.addAttribute("itemsImage", new ItemsImage());
						msg = itemsImage.getCode() + "  Added Succesfully";
					} else {
						model.addAttribute("msg", temp);
					}
				}
			} catch (IOException e) {
				System.out.println(e.toString());
				msg = "File upload Exception";
			}
		}
		model.addAttribute("msg", msg);
		model.addAttribute("items", new Items());
		model.addAttribute("categorieslist", itemsDAO.getAllCategoriesList());
		model.addAttribute("defaultTab_Selector", "#tabs-1");
		return "adminpage";
	}

	@RequestMapping(value = "/adminpage_additem", method = RequestMethod.POST, params = { "addcancel" })
	public String for_add_cancel(Model model) {
		model.addAttribute("itemsImage", new ItemsImage());
		model.addAttribute("items", new Items());
		model.addAttribute("defaultTab_Selector", "#tabs-1");
		return "adminpage";
	}

	@RequestMapping(value = "/adminpage_edititem", method = RequestMethod.POST, params = { "search" })
	public String searchitems(@RequestParam("code") String code, Model model) {
		Items items = new Items();
		String msg;
		if (code.length() == 0) {
			msg = "Enter Item code to search";
		} else {
			ArrayList<String> item_codes = itemsDAO.searchItems(code);
			if (item_codes.size() == 0) { // no items found
				msg = "No Items Found";
			} else if (item_codes.size() == 1) {
				items = itemsDAO.getItemDetails(item_codes.get(0));
				if (code != items.getCode()) {
					msg = "Search Item changed to : " + items.getCode();
				} else {
					msg = "";
				}
				model.addAttribute("items", items);
			} else {
				msg = "Multiple items found  " + item_codes;
			}
		}
		model.addAttribute("msg", msg);
		model.addAttribute("items", items);
		model.addAttribute("itemsImage", new ItemsImage());
		model.addAttribute("defaultTab_Selector", "#tabs-2");
		return "adminpage";
	}

	@RequestMapping(value = "/adminpage_edititem", method = RequestMethod.POST, params = { "updateitem" })
	public String edititems(@Valid Items items, BindingResult bindingResult, Model model) {
		String msg = "";
		if (!bindingResult.hasErrors()) {
			if (itemsDAO.editItems(items)) {
				msg = items.getCode() + " Updated";
				model.addAttribute("items", new Items());
			} else {
				msg = "Error while Updating";
			}
		}
		model.addAttribute("msg", msg);
		model.addAttribute("itemsImage", new ItemsImage());
		model.addAttribute("defaultTab_Selector", "#tabs-2");
		return "adminpage";
	}

	@RequestMapping(value = "/adminpage_edititem", method = RequestMethod.POST, params = { "editclear" })
	public String for_edit_cancel(Model model) {
		model.addAttribute("items", new Items());
		model.addAttribute("itemsImage", new ItemsImage());
		model.addAttribute("defaultTab_Selector", "#tabs-2");
		return "adminpage";
	}

	@RequestMapping(value = "/adminpage_edititem", method = RequestMethod.POST, params = { "editdelete" })
	public String for_edit_delete(@Valid Items items, BindingResult bindingResult, Model model) {
		String msg = "";
		if (!bindingResult.hasErrors()) {
			if (itemsDAO.deleteItems(items.getCode())) {
				msg = items.getCode() + " is deleted";
				model.addAttribute("items", new Items());
			} else {
				msg = items.getCode() + " Item not found";
			}
		}
		model.addAttribute("msg", msg);
		model.addAttribute("itemsImage", new ItemsImage());
		model.addAttribute("categorieslist", itemsDAO.getAllCategoriesList());
		model.addAttribute("defaultTab_Selector", "#tabs-2");
		return "adminpage";
	}
}
