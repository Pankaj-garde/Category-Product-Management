package com.example.nimap_infotech_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nimap_infotech_test.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
}
