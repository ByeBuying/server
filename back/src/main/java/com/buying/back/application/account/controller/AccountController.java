package com.buying.back.application.account.controller;

import static com.buying.back.util.response.CommonResponseCode.SUCCESS;

import com.buying.back.application.account.service.AccountService;
import com.buying.back.application.account.service.vo.AccountCouponVO;
import com.buying.back.application.account.service.vo.AccountDefaultVO;
import com.buying.back.application.common.dto.PagingDTO;
import com.buying.back.application.coupon.service.vo.CouponVO;
import com.buying.back.infra.config.security.loginuser.LoginUser;
import com.buying.back.util.response.CommonResponse;
import com.buying.back.util.response.CommonResponseCode;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pub/accounts")
@RequiredArgsConstructor
public class AccountController {

  private final AccountService accountService;

  @GetMapping("/my-information")
  public CommonResponse<AccountDefaultVO> getMyInformation(
    @AuthenticationPrincipal LoginUser loginUser) {
    AccountDefaultVO vo = accountService.getMyInformation(loginUser.getAccountId());
    return new CommonResponse<>(vo, SUCCESS);
  }

  @GetMapping("/coupons/my-coupon-list")
  public CommonResponse<Page<AccountCouponVO>> getMyCouponList(@AuthenticationPrincipal LoginUser loginUser,
    @Valid PagingDTO dto) {
    Page<AccountCouponVO> list = accountService.getMyCouponList(loginUser.getAccountId(), dto);
    return new CommonResponse<>(list, CommonResponseCode.SUCCESS);
  }
}
