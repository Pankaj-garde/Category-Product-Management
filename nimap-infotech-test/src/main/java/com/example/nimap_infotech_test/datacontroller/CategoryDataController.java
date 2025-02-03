package com.example.nimap_infotech_test.datacontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.nimap_infotech_test.entity.Category;
import com.example.nimap_infotech_test.services.CategoryServices;
import org.springframework.data.domain.Page;
@RestController
@RequestMapping("/api/categories")
public class CategoryDataController {
	
	@Autowired
	private CategoryServices categoryservices;
	
	// 1. Get all Category with pagination
	@GetMapping
    public Page<Category> getAllCategories(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size) {
		System.out.println("call method");
		return categoryservices.getAllCategory(page, size);
    }
    
 // 2. Create a new Category
    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Category category) {
    	System.out.println("product id" +category.getCategoryName());
    	String result = categoryservices.addCategory(category);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    
   
    // 3.get by Id
    
    @GetMapping("/{productId}") // Endpoint: /api/categories/{id}
    public ResponseEntity<?> getProductById(@PathVariable int productId) {
    	System.out.println("id "+productId);
        // Fetch the category by ID
    	Category c = categoryservices.getById(productId);

        // Check if the category exists
        if (c != null) {
            return ResponseEntity.ok(c); // Return 200 OK with the category
        } else {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found with ID: " + productId);
        }
    }

    
//4. update 
    
    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProductById(@PathVariable int productId, @RequestBody Category category) {
        System.out.println("Updating product with ID: " + productId);
        String messege = categoryservices.updateById(productId, category);
        
        if (messege.equalsIgnoreCase("Category updated successfully!")) {
            return ResponseEntity.ok(messege);  // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Product with ID " + productId + " not found");
        }
    }
    
//5. delete Category
    
    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable int id){
    	return categoryservices.deleteById(id);
    }
}
