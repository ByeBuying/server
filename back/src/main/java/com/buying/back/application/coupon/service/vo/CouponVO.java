package com.buying.back.application.coupon.service.vo;

import com.buying.back.application.coupon.domain.Coupon;
import com.querydsl.core.annotations.QueryProjection;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CouponVO {

  private Long id;
  private String name;
  private BigDecimal discountPercent;
  private BigDecimal orderMinimumAmount;
  private int expirationPeriod;
  private boolean activated;

  @QueryProjection
  public CouponVO(Long id, String name, BigDecimal discountPercent, BigDecimal orderMinimumAmount,
    int expirationPeriod, boolean activated) {
    this.id = id;
    this.name = name;
    this.discountPercent = discountPercent;
    this.orderMinimumAmount = orderMinimumAmount;
    this.expirationPeriod = expirationPeriod;
    this.activated = activated;
  }

  public static CouponVO valueOf(Coupon coupon) {
    CouponVO vo = new CouponVO();
    vo.setId(coupon.getId());
    vo.setName(coupon.getName());
    vo.setDiscountPercent(coupon.getDiscountPercent());
    vo.setOrderMinimumAmount(coupon.getOrderMinimumAmount());
    vo.setExpirationPeriod(coupon.getExpirationPeriod());
    vo.setActivated(coupon.isActivated());
    return vo;
  }
}
