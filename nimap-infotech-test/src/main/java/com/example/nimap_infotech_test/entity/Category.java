package com.example.nimap_infotech_test.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int categoryId;
	@Column(unique = true,nullable = false)
	private String categoryName;
	
	 @OneToMany(mappedBy = "category")
	 @JsonBackReference
	  private List<Product> product = new ArrayList<>();
	
	public Category() {
		// TODO Auto-generated constructor stub
	}
	

	public Category(int categoryId, String categoryName, List<Product> product) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.product = product;
	}


	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}	
	
}
