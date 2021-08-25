package com.coupon.service.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coupon.service.app.model.Coupon;
import com.coupon.service.app.repository.CouponRepository;

@RestController
@RequestMapping("/couponapi")
public class CouponRestController {
	
	private static final Logger log = LoggerFactory.getLogger(CouponRestController.class);
	
	@Autowired
	CouponRepository couponRepository;

	@PostMapping("/coupons")
	public Coupon create(@RequestBody Coupon coupon) {
		return couponRepository.save(coupon);
	}
	
	@GetMapping("/coupons/{code}")
	public Coupon getCouponByCode(@PathVariable("code") String code) {
		System.out.println("SERVER 2");
		log.info("Inside getCoupon Handler");
		return couponRepository.findByCode(code);
	}
	
}
