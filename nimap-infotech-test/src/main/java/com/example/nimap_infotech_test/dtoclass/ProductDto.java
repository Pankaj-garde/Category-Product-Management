package com.example.nimap_infotech_test.dtoclass;

public class ProductDto {

	private String  productName;
	private double prices;
	private int categoryId;
	
	
	public ProductDto() {
		// TODO Auto-generated constructor stub
	}


	public ProductDto(String productName, double prices, int categoryId) {
		super();
		this.productName = productName;
		this.prices = prices;
		this.categoryId = categoryId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public double getPrices() {
		return prices;
	}


	public void setPrices(double prices) {
		this.prices = prices;
	}


	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
}
