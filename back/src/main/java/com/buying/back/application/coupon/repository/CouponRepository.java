package com.buying.back.application.coupon.repository;

import com.buying.back.application.coupon.domain.Coupon;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long>, CouponRepositoryCustom {

  Optional<Coupon> findByNameStartsWith(String name);

}
