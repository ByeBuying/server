package com.buying.back.application.coupon.service;

import com.buying.back.application.coupon.code.exception.CouponException;
import com.buying.back.application.coupon.code.exception.CouponException.CouponExceptionCode;
import com.buying.back.application.coupon.controller.dto.CreateCouponDTO;
import com.buying.back.application.coupon.controller.dto.SearchCouponDTO;
import com.buying.back.application.coupon.controller.dto.UpdateCouponDTO;
import com.buying.back.application.coupon.domain.Coupon;
import com.buying.back.application.coupon.repository.CouponRepository;
import com.buying.back.application.coupon.service.vo.CouponVO;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CouponService {

  private final CouponRepository couponRepository;

  @Transactional
  public CouponVO createCoupon(CreateCouponDTO dto) {
    Coupon coupon = couponRepository.findByNameStartsWith(dto.getName())
      .orElse(null);

    if (Objects.nonNull(coupon)) {
      throw new CouponException(CouponExceptionCode.ALREADY_EXIST_COUPON);
    }

    coupon = Coupon.initCoupon()
      .dto(dto)
      .build();
    couponRepository.save(coupon);

    return CouponVO.valueOf(coupon);
  }

  public Page<CouponVO> getCouponList(SearchCouponDTO dto) {
    return couponRepository.findAll(dto.getPageRequest(), dto);
  }

  @Transactional
  public CouponVO updateCoupon(Long couponId, UpdateCouponDTO dto) {
    Coupon coupon = couponRepository.findById(couponId)
      .orElseThrow(() -> new CouponException(CouponExceptionCode.NOT_FOUND_COUPON));

    coupon.setExpirationPeriod(dto.getExpirationPeriod());

    if (Boolean.TRUE.equals(dto.getActivated())) {
      coupon.setActivated(dto.getActivated());
      coupon.setDeActivateDateTime(null);
    } else {
      coupon.setActivated(dto.getActivated());
      coupon.setDeActivateDateTime(LocalDateTime.now());
    }

    couponRepository.save(coupon);
    return CouponVO.valueOf(coupon);
  }

}
