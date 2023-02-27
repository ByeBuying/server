package com.buying.back.application.account.service.vo;

import com.buying.back.application.account.domain.AccountCouponRelation;
import com.buying.back.application.coupon.domain.Coupon;
import com.querydsl.core.annotations.QueryProjection;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountCouponVO {

  private Long accountCouponId;
  private String couponName;
  private BigDecimal discountPercent;
  private BigDecimal orderMinimumAmount;
  private LocalDate couponIssuedDate;
  private LocalDate expirationDate;
  private Boolean used;
  private LocalDateTime usedDateTime;

  @QueryProjection
  public AccountCouponVO(Long accountCouponId, String couponName, BigDecimal discountPercent,
    BigDecimal orderMinimumAmount, LocalDate couponIssuedDate, LocalDate expirationDate,
    Boolean used,
    LocalDateTime usedDateTime) {
    this.accountCouponId = accountCouponId;
    this.couponName = couponName;
    this.discountPercent = discountPercent;
    this.orderMinimumAmount = orderMinimumAmount;
    this.couponIssuedDate = couponIssuedDate;
    this.expirationDate = expirationDate;
    this.used = used;
    this.usedDateTime = usedDateTime;
  }

  public static AccountCouponVO valueOf(AccountCouponRelation relation, Coupon coupon) {
    AccountCouponVO vo = new AccountCouponVO();
    vo.setAccountCouponId(relation.getId());
    vo.setCouponName(coupon.getName());
    vo.setDiscountPercent(coupon.getDiscountPercent());
    vo.setOrderMinimumAmount(coupon.getOrderMinimumAmount());
    vo.setCouponIssuedDate(relation.getCouponIssuedDate());
    vo.setExpirationDate(relation.getExpirationDate());
    vo.setUsed(relation.isUsed());
    vo.setUsedDateTime(relation.getUsedDateTime());
    return vo;
  }
}
