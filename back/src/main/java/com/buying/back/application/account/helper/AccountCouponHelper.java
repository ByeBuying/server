package com.buying.back.application.account.helper;

import com.buying.back.application.account.code.exception.AccountCouponException;
import com.buying.back.application.account.code.exception.AccountCouponException.AccountCouponExceptionCode;
import com.buying.back.application.account.code.exception.AccountException;
import com.buying.back.application.account.code.exception.AccountException.AccountExceptionCode;
import com.buying.back.application.account.domain.Account;
import com.buying.back.application.account.domain.AccountCouponRelation;
import com.buying.back.application.account.repository.AccountCouponRelationRepository;
import com.buying.back.application.account.repository.AccountRepository;
import com.buying.back.application.account.service.vo.AccountCouponVO;
import com.buying.back.application.common.dto.PagingDTO;
import com.buying.back.application.coupon.code.exception.CouponException;
import com.buying.back.application.coupon.code.exception.CouponException.CouponExceptionCode;
import com.buying.back.application.coupon.domain.Coupon;
import com.buying.back.application.coupon.repository.CouponRepository;
import com.buying.back.application.coupon.service.vo.CouponVO;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountCouponHelper {

  private final AccountRepository accountRepository;
  private final CouponRepository couponRepository;
  private final AccountCouponRelationRepository accountCouponRelationRepository;

  @Transactional
  public void assignCoupon(Long accountId, Long couponId) {
    Account account = accountRepository.findById(accountId)
      .orElseThrow(() -> new AccountException(AccountExceptionCode.NOT_FOUND_ACCOUNT));

    Coupon coupon = couponRepository.findById(couponId)
      .orElseThrow(() -> new CouponException(CouponExceptionCode.NOT_FOUND_COUPON));

    AccountCouponRelation relation = accountCouponRelationRepository
      .findByAccountAndCoupon(account, coupon)
      .orElse(null);

    if (Objects.nonNull(relation)) {
      throw new AccountCouponException(AccountCouponExceptionCode.ALREADY_GRANTED_COUPON_ACCOUNT);
    }

    relation = new AccountCouponRelation(account, coupon);
    accountCouponRelationRepository.save(relation);
  }

  public Page<AccountCouponVO> getCouponListByAccount(PagingDTO dto, Account account) {
    return couponRepository.findAllAccountCoupon(dto.getPageRequest(), account);
  }

}
