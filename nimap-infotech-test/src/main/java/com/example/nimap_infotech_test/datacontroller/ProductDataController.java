package com.example.nimap_infotech_test.datacontroller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.example.nimap_infotech_test.dtoclass.ProductDto;
import com.example.nimap_infotech_test.entity.Product;
import com.example.nimap_infotech_test.services.ProductServices;

@RestController
@RequestMapping("/api/products")
public class ProductDataController {
	@Autowired(required = true)
    private ProductServices productServices;

    // 1. Get all products with pagination
	@GetMapping
    public Page<Product> getAllProducts(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size) {
		System.out.println("call method");
        return productServices.getAllData(page, size);
    }
    // 2. Create a new product
    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductDto productDto) {
    	System.out.println("product id" +productDto.getCategoryId());
    	System.out.println("poduct name " + productDto.getProductName());
        String result = productServices.addData(productDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    
    // 3.get by Id
    
    @GetMapping("/{productId}") // Endpoint: /api/products/{productId}
    public ResponseEntity<?> getProductById(@PathVariable int productId) {
    	System.out.println("id "+productId);
        // Fetch the category by ID
        Product p = productServices.getById(productId);

        // Check if the category exists
        if (p != null) {
            return ResponseEntity.ok(p); // Return 200 OK with the category
        } else {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found with ID: " + productId);
        }
    }
    
  //4. update 
    
    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProductById(@PathVariable int productId, @RequestBody ProductDto productDto) {
        System.out.println("Updating product with ID: " + productId);
        
        String messege = productServices.updateById(productId, productDto);
        
        if (messege.equalsIgnoreCase("Product updated successfully!")) {
            return ResponseEntity.ok(messege);  // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Product with ID " + productId + " not found");
        }
    }


    //5. delete product
    
    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable int id){
    	return productServices.deleteById(id);
    }

}
