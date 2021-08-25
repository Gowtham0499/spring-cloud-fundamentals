package com.coupon.service.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coupon.service.app.model.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

}
