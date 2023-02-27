package com.buying.back.application.account.code.exception;

import com.buying.back.application.common.exception.CommonException;
import com.buying.back.util.response.ResponseCode;

public class AccountCouponException extends CommonException {

  public AccountCouponException(AccountCouponExceptionCode code) {
    super(code);
  }

  public AccountCouponException(AccountCouponExceptionCode code, String detailMessage) {
    super(code, detailMessage);
  }

  public AccountCouponException(String detailMessage) {
    super(AccountCouponExceptionCode.INVALID_ACCOUNT_COUPON, detailMessage);
  }

  public enum AccountCouponExceptionCode implements ResponseCode {
    INVALID_ACCOUNT_COUPON("ACE-001", "invalid account coupon"),
    ALREADY_GRANTED_COUPON_ACCOUNT("ACE-002", "this coupon has already been granted"),
    NOT_FOUND_GRANTED_COUPON_ACCOUNT("ACE-003", "not found account coupon"),
    ;

    private final String code;
    private final String message;

    AccountCouponExceptionCode(String code, String message) {
      this.code = code;
      this.message = message;
    }

    @Override
    public String getCode() {
      return code;
    }

    @Override
    public String getMessage() {
      return message;
    }
  }
}
