package com.product.service.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.service.app.dto.Coupon;
import com.product.service.app.model.Product;
import com.product.service.app.repository.ProductRepository;
import com.product.service.app.restclient.CouponClient;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CouponClient couponClient;

	@PostMapping("/products")
	@Retry(name="product-api", fallbackMethod = "handleError")
	public Product create(@RequestBody Product product) {
		Coupon coupon = couponClient.getCoupon(product.getCouponCode());
		product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
		return productRepository.save(product);
	}
	
	public Product handleError(Product product, Exception exception) {
		System.out.println("Inside Handle Error");
		return product;
	}

}
