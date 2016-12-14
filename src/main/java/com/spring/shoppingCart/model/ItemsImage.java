package com.spring.shoppingCart.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class ItemsImage {
	
	@NotEmpty(message = "Category required")
	private String category;

	@NotEmpty(message="Item Name required")
	private String name;
	
	@NotEmpty(message="Item Code required")
	private String code;
	
	@NotEmpty(message="Item Description required")
	private String description;
	
	@NotNull(message="Item Price required")
	@Min(value = 1, message = "Invalid Price")
	private int price;
	
	@NotNull(message="Item Quantity required")
	@Min(value = 1, message = "Invalid Quantity")
	private int quantity;
	
	@NotNull(message="Image required")
	private MultipartFile image;
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}
}
