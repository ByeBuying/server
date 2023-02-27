package com.buying.back.application.coupon.controller;

import com.buying.back.application.coupon.controller.dto.CreateCouponDTO;
import com.buying.back.application.coupon.controller.dto.SearchCouponDTO;
import com.buying.back.application.coupon.controller.dto.UpdateCouponDTO;
import com.buying.back.application.coupon.service.CouponService;
import com.buying.back.application.coupon.service.vo.CouponVO;
import com.buying.back.util.response.CommonResponse;
import com.buying.back.util.response.CommonResponseCode;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sys/coupons")
@RequiredArgsConstructor
public class CouponManagementController {

  private final CouponService couponService;

  @PostMapping
  public CommonResponse<CouponVO> createCoupon(@Valid @RequestBody CreateCouponDTO dto) {
    CouponVO vo = couponService.createCoupon(dto);
    return new CommonResponse<>(vo, CommonResponseCode.SUCCESS);
  }

  @GetMapping
  public CommonResponse<Page<CouponVO>> getCouponList(SearchCouponDTO dto) {
    Page<CouponVO> list = couponService.getCouponList(dto);
    return new CommonResponse<>(list, CommonResponseCode.SUCCESS);
  }

  @PutMapping("/{coupon-id}")
  public CommonResponse<CouponVO> updateCoupon(
    @PathVariable(value = "coupon-id") Long couponId, @RequestBody UpdateCouponDTO dto) {
    CouponVO vo = couponService.updateCoupon(couponId, dto);
    return new CommonResponse<>(vo, CommonResponseCode.SUCCESS);
  }

}
