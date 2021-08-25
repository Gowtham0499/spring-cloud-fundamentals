package com.product.service.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.service.app.model.Product;
import com.product.service.app.repository.ProductRepository;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {
	
	@Autowired
	ProductRepository productRepository;
	
	@PostMapping("/products")
	public Product create(@RequestBody Product product) {
		return productRepository.save(product);
	}
	

}
