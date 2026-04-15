package com.fooddelivery.service;

import com.fooddelivery.model.Coupon;

import java.util.ArrayList;
import java.util.List;

public class CouponService {
    private List<Coupon> coupons;

    public CouponService() {
        this.coupons = new ArrayList<>();
    }

    public void addCoupon(Coupon coupon) {
        this.coupons.add(coupon);
    }

    public Coupon getCoupon(String couponCode) {
        for (Coupon cp : this.coupons) {
            if(cp.getCode().equals(couponCode)) {
                return cp;
            }
        }
        return null;
    }

    public List<Coupon> getAllCoupon(){
        return coupons;
    }

    public boolean isValidCoupon(String code){
        if(getCoupon(code) == null) { return false; }
        if(!getCoupon(code).isActive()) { return false; }

        return true;
    }
}
