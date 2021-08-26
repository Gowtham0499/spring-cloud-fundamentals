package com.product.service.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
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
@RefreshScope
public class ProductRestController {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CouponClient couponClient;
	
	@Value("${custom.property}")
	private String prop;

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
	
	@GetMapping("/prop")
	public String customConfigProperty() {
		return this.prop;
	}

}
