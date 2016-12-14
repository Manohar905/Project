package com.spring.shoppingCart.model;

public class Mycart {
	private String codes;
	private int quantity;
	private int price;
	private int total;

	public Mycart(String codes, int quantity, int price) {
		this.codes = codes;
		this.quantity = quantity;
		this.price = price;
		this.total = price * quantity;
	}

	public Mycart(String codes, int quantity, int price, int total) {
		this.codes = codes;
		this.quantity = quantity;
		this.price = price;
		this.total = total;
	}

	public String getCodes() {
		return codes;
	}

	public void setCodes(String codes) {
		this.codes = codes;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
