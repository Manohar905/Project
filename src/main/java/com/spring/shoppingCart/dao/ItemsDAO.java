package com.spring.shoppingCart.dao;

import java.util.ArrayList;
import java.util.List;

import com.spring.shoppingCart.model.Items;
import com.spring.shoppingCart.model.ItemsImage;

public interface ItemsDAO {

	public String addItems(ItemsImage itemsImage);

	public ArrayList<String> searchItems(String code);

	public Items getItemDetails(String code);

	public boolean editItems(Items items);

	public boolean deleteItems(String code);

	public int getmaxPrice();

	public List<Items> getConditionalItemsList(String search, String[] categories, String[] names, int maxPrice);
	
	// return search results to client page
	public List<Items> getAllItemsList(String search);

	public int countToGetAllItems(String search);

	// return ALL results to client page
	public List<Items> getAllItemsList();

	public List<String> getAllNamesList();

	public List<String> getAllCategoriesList();
}
