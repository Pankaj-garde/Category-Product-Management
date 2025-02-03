package com.example.nimap_infotech_test.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.nimap_infotech_test.dtoclass.ProductDto;
import com.example.nimap_infotech_test.entity.Category;
import com.example.nimap_infotech_test.entity.Product;
import com.example.nimap_infotech_test.repository.CategoryRepository;
import com.example.nimap_infotech_test.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class ProductServices {

	@Autowired(required = true)
	private ProductRepository productRepo;

	@Autowired
	private CategoryRepository categoryrepo;

	public String addData(ProductDto product) {

		System.out.println("poduct name " + product.getProductName());
		Category category = categoryrepo.findById(product.getCategoryId()).orElse(null);

		if (category == null) {
			return "Category not found with ID: " + product.getCategoryId();
		}
		try {
			// Save the product to the database

			Product p = new Product();
			p.setCategory(category);
			p.setPrice(product.getPrices());
			p.setProductName(product.getProductName());
			productRepo.save(p);
			return "Product added successfully!";
		} catch (DataIntegrityViolationException e) {
			// Handle database constraint violations (e.g., duplicate entries)
			return "Failed to add product: Duplicate entry or invalid data.";
		} catch (Exception e) {
			// Log the exception for debugging purposes
			e.printStackTrace();
			return "Failed to add product due to an unexpected error.";
		}
	}

	public Page<Product> getAllData(int page, int size) {
	    // Create a Pageable object with the requested page and size
	    Pageable pageable = PageRequest.of(page, size);

	    // Fetch products using pagination
	    Page<Product> productPage = productRepo.findAll(pageable);

	    return productPage;
	}

	public Product getById(int id) {
		System.out.println("id " + id);
		Product product = productRepo.findById(id).orElse(null); // Return null if not found
		return product;
	}

	public String updateById(int id, ProductDto updatedProduct) {

		Product existingProduct = productRepo.findById(id).orElse(null); // Return null if not found

		if (existingProduct != null) {
			existingProduct.setProductName(updatedProduct.getProductName());
			existingProduct.setPrice(updatedProduct.getPrices());
			productRepo.save(existingProduct);
			return "Product updated successfully!";
		} else {
			return "Product not found with ID: " + id;
		}
	}

	public String deleteById(int id) {
		Product existingProduct = productRepo.findById(id).orElse(null); // Return null if not found
		if (existingProduct == null) {
			return "Product not found with ID: " + id;
		}
		try {
			productRepo.deleteById(id);
			return "Product deleted successfully!";
		} catch (EmptyResultDataAccessException e) {
			return "Product not found with ID: " + id;
		} catch (Exception e) {
			return "Failed to delete product due to an unexpected error.";
		}
	}

}
