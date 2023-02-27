package com.buying.back.application.account.domain;

import com.buying.back.application.common.domain.Base;
import com.buying.back.application.coupon.domain.Coupon;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account_coupon_relation")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountCouponRelation extends Base {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "account_coupon_relation_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id", nullable = false, referencedColumnName = "account_id")
  @JsonBackReference
  private Account account;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "coupon_id", nullable = false, referencedColumnName = "coupon_id")
  @JsonBackReference
  private Coupon coupon;

  @Column(name = "coupon_issued_date", nullable = false)
  private LocalDate couponIssuedDate;
  @Column(name = "expiration_date", nullable = false)
  private LocalDate expirationDate;

  @Setter
  private LocalDateTime usedDateTime;

  @Setter
  private boolean used;

  public AccountCouponRelation(Account account, Coupon coupon) {
    this.account = account;
    this.coupon = coupon;
    this.couponIssuedDate = LocalDate.now();
    this.expirationDate = LocalDate.now().plusDays(coupon.getExpirationPeriod());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AccountCouponRelation)) {
      return false;
    }
    AccountCouponRelation that = (AccountCouponRelation) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
