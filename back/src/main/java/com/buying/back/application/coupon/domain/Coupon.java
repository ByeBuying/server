package com.buying.back.application.coupon.domain;

import com.buying.back.application.common.domain.Base;
import com.buying.back.application.coupon.controller.dto.CreateCouponDTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "coupon")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon extends Base {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "coupon_id")
  private Long id;

  // TODO: 2023/02/27 coupon code 컬럼 추가: yyyyMMdd_Keyword

  @Column(name = "coupon_name", nullable = false, unique = true)
  private String name;

  @Column(name = "discount_percent", nullable = false)
  private BigDecimal discountPercent;

  @Column(name = "order_minimum_amount", nullable = false)
  private BigDecimal orderMinimumAmount;

  @Setter
  @Column(name = "expiration_period", nullable = false)
  private int expirationPeriod;

  @Setter
  private boolean activated;

  @Setter
  private LocalDateTime deActivateDateTime;

  @Builder(builderClassName = "initCoupon", builderMethodName = "initCoupon")
  public Coupon(CreateCouponDTO dto) {
    this.name=dto.getName();
    this.discountPercent=dto.getDiscountPercent();
    this.orderMinimumAmount=dto.getOrderMinimumAmount();
    this.expirationPeriod=dto.getExpirationPeriod();
    this.activated=true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Coupon)) {
      return false;
    }
    Coupon coupon = (Coupon) o;
    return Objects.equals(id, coupon.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
