package com.example.nimap_infotech_test.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;
	
	@Column(nullable = false)
	private String productName;
	@Column(nullable = false)
	private double price;
	
	@ManyToOne
	@JoinColumn(name = "category_id")  // This maps to category_id directly
	private Category category;

	
	
	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Product(int productId, String productName, double price, Category category) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.category = category;
	}



	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
