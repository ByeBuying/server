package com.buying.back.application.coupon.repository;

import com.buying.back.application.account.domain.Account;
import com.buying.back.application.account.service.vo.AccountCouponVO;
import com.buying.back.application.coupon.controller.dto.SearchCouponDTO;
import com.buying.back.application.coupon.service.vo.CouponVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CouponRepositoryCustom {

  Page<CouponVO> findAll(Pageable pageable, SearchCouponDTO dto);
  Page<AccountCouponVO> findAllAccountCoupon(Pageable pageable, Account account);
}
