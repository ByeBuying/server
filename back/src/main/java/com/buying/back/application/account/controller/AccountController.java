package com.buying.back.application.account.controller;

import static com.buying.back.util.response.CommonResponseCode.SUCCESS;

import com.buying.back.application.account.service.AccountService;
import com.buying.back.application.account.service.vo.AccountDefaultVO;
import com.buying.back.infra.config.security.loginuser.LoginUser;
import com.buying.back.util.response.CommonResponse;
import lombok.RequiredArgsConstructor;
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
}
