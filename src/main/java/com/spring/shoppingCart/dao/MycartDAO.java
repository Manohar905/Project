package com.spring.shoppingCart.dao;

import java.util.List;

import com.spring.shoppingCart.model.Mycart;

public interface MycartDAO {

	public boolean addToMycart(Mycart mycart);

	public boolean deleteFromMycart(String code);

	public boolean editQuantity(String code, int quantity);

	public boolean findInMycart(String code);

	public List<Mycart> getMycart();

	public void clearMycart();

	public int countMycart();

	public int totalMycart();
}
