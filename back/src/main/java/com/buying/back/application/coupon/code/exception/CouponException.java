package com.buying.back.application.coupon.code.exception;

import com.buying.back.application.common.exception.CommonException;
import com.buying.back.util.response.ResponseCode;

public class CouponException extends CommonException {

  public CouponException(CouponExceptionCode code) {
    super(code);
  }

  public CouponException(CouponExceptionCode code, String detailMessage) {
    super(code, detailMessage);
  }

  public CouponException(String detailMessage) {
    super(CouponExceptionCode.INVALID_COUPON, detailMessage);
  }

  public enum CouponExceptionCode implements ResponseCode {
    INVALID_COUPON("CEC-001", "invalid coupon"),
    ALREADY_EXIST_COUPON("CEC-002", "already exist coupon name"),
    NOT_FOUND_COUPON("CEC-003", "coupon not found"),
    EXPIRATION_COUPON("CEC-004", "this coupon is expired"),
    NOT_ACTIVATE_COUPON("CEC-005", "this coupon is deactivated"),
    ;

    private final String code;
    private final String message;

    CouponExceptionCode(String code, String message) {
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
