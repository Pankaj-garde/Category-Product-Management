package com.example.nimap_infotech_test.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.example.nimap_infotech_test.entity.Category;
import com.example.nimap_infotech_test.entity.Product;
import com.example.nimap_infotech_test.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class CategoryServices {

	@Autowired
	private CategoryRepository categoryRepo;

// add Category 
	public String addCategory(Category category) {
		try {
			categoryRepo.save(category);
			return "Category added successfully!";
		} catch (DataIntegrityViolationException e) {
			return "Failed to add category: Duplicate entry or invalid data.";
		} catch (Exception e) {
			return "Failed to add category due to an unexpected error.";
		}
	}

// get all Category 
	public Page<Category> getAllCategory(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return categoryRepo.findAll(pageable);
    }

	// get by id
	public Category getById(int id) {
		System.out.println("Category Id "+ id);
		Category category = categoryRepo.findById(id).get();
		return category;
	}

	// update Category 
	public String updateById(int id, Category updatedCategory) {
		Category existingCategory = categoryRepo.findById(id).orElse(null); // Return null if not found

		if (existingCategory != null) {
			existingCategory.setCategoryName(updatedCategory.getCategoryName());
			categoryRepo.save(existingCategory);
			return "Category updated successfully!";
		} else {
			return "Category not found with ID: " + id;
		}
	}

	
	// delete Category
	public String deleteById(int id) {
		Category existingCategory = categoryRepo.findById(id).orElse(null); // Return null if not found
		if (existingCategory == null) {
			return "Category not found with ID: " + id;
		}
		try {
			categoryRepo.deleteById(id);
			return "Category deleted successfully!";
		} catch (DataIntegrityViolationException e) {
			return "Cannot delete category because it is associated with one or more products " + id;
		} catch (Exception e) {
			return "Failed to delete category due to an unexpected error.";
		}
	}

}
