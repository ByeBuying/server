package com.buying.back.application.account.code.exception;

import com.buying.back.application.common.exception.CommonException;
import com.buying.back.util.response.ResponseCode;

public class AccountException extends CommonException {

  public AccountException(AccountExceptionCode code) {
    super(code);
  }

  public AccountException(AccountExceptionCode code, String detailMessage) {
    super(code, detailMessage);
  }

  public AccountException(String detailMessage) {
    super(AccountExceptionCode.INVALID_ACCOUNT, detailMessage);
  }

  public enum AccountExceptionCode implements ResponseCode {
    INVALID_ACCOUNT("AEC-001", "invalid account"),
    ALREADY_EXIST_ACCOUNT("AEC-002", "already exist account"),
    NOT_FOUND_ACCOUNT("AEC-003", "account not found"),
    NOT_CHANGE_PASSWORD("AEC-004", "Password not changed"),
    ALREADY_ACTIVATED_ACCOUNT("AEC-005", "already activated account"),
    ALREADY_DEACTIVATED_ACCOUNT("AEC-006", "already deactivated account"),
    DEACTIVATED_ACCOUNT("AEC-007", "deactivated account, try log in this account"),
    NOT_AUTHORIZED("AEC-008", "not authorized"),
    NOT_MATCHES_NEW_PASSWORD("AEC-009", "not matches new password"),
    ;

    private final String code;
    private final String message;

    AccountExceptionCode(String code, String message) {
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
