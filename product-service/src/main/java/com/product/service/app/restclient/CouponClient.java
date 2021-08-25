package com.product.service.app.restclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.product.service.app.dto.Coupon;

@FeignClient("GATEWAY-SERVICE")
public interface CouponClient {

	@GetMapping("/couponapi/coupons/{code}")
	Coupon getCoupon(@PathVariable("code") String code);
	
}
