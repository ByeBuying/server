package com.buying.back.application.coupon.repository;

import static com.buying.back.application.account.domain.QAccount.account;
import static com.buying.back.application.coupon.domain.QAccountCouponRelation.accountCouponRelation;
import static com.buying.back.application.coupon.domain.QCoupon.coupon;

import com.buying.back.application.account.domain.Account;
import com.buying.back.application.coupon.controller.dto.SearchCouponDTO;
import com.buying.back.application.coupon.domain.Coupon;
import com.buying.back.application.coupon.service.vo.CouponVO;
import com.buying.back.application.coupon.service.vo.QCouponVO;
import com.buying.back.util.querydsl.CustomQuerydslRepositorySupport;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import java.util.Objects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CouponRepositoryImpl extends CustomQuerydslRepositorySupport
  implements CouponRepositoryCustom {

  public CouponRepositoryImpl() {
    super(Coupon.class);
  }

  @Override
  public Page<CouponVO> findAll(Pageable pageable, SearchCouponDTO dto) {
    BooleanBuilder whereCondition = getCouponWhereCondition(dto);

    JPAQuery<CouponVO> query = select(getCouponVO())
      .from(coupon)
      .where(whereCondition)
      .orderBy(coupon.expirationDate.desc());

    JPAQuery<Long> countQuery = select(coupon.count())
      .from(coupon)
      .where(whereCondition);

    return applyPagination(pageable, query, countQuery);
  }

  @Override
  public Page<CouponVO> findAllByAccount(Pageable pageable, Account byAccount) {
    JPAQuery<CouponVO> query = select(getCouponVO())
      .from(coupon)
      .innerJoin(accountCouponRelation).on(coupon.eq(accountCouponRelation.coupon))
      .innerJoin(accountCouponRelation.account, account)
      .where(account.eq(byAccount)
        .and(coupon.activated.isTrue()))
      .orderBy(coupon.expirationDate.desc());

    JPAQuery<Long> countQuery = select(coupon.count())
      .from(coupon)
      .innerJoin(accountCouponRelation).on(coupon.eq(accountCouponRelation.coupon))
      .innerJoin(accountCouponRelation.account, account)
      .where(account.eq(byAccount)
        .and(coupon.activated.isTrue()));

    return applyPagination(pageable, query, countQuery);
  }

  private QCouponVO getCouponVO() {
    return new QCouponVO(
      coupon.id,
      coupon.name,
      coupon.discountPercent,
      coupon.orderMinimumAmount,
      coupon.expirationDate,
      coupon.activated
    );
  }

  private BooleanBuilder getCouponWhereCondition(SearchCouponDTO dto) {
    BooleanBuilder whereCondition = new BooleanBuilder();

    if (Objects.nonNull(dto.getActivated())) {
      whereCondition.and(coupon.activated.eq(dto.getActivated()));
    }

    if (Objects.nonNull(dto.getExpirationDate())) {
      whereCondition.and(coupon.expirationDate.goe(dto.getExpirationDate()));
    }

    return whereCondition;
  }
}
