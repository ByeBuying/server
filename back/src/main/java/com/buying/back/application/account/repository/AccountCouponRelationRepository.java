package com.buying.back.application.account.repository;

import com.buying.back.application.account.domain.Account;
import com.buying.back.application.account.domain.AccountCouponRelation;
import com.buying.back.application.coupon.domain.Coupon;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountCouponRelationRepository extends JpaRepository<AccountCouponRelation, Long> {

  Optional<AccountCouponRelation> findByAccountAndCoupon(Account account, Coupon coupon);
}
