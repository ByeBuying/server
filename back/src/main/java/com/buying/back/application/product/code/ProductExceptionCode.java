package com.buying.back.application.product.code;

import com.buying.back.util.response.ResponseCode;

public enum ProductExceptionCode implements ResponseCode {
    NOT_FOUND_PRODUCT("PEC-001", "product not found"),
    NOT_FOUND_OPTION("PEC-002", "option not found"),
    NOT_FOUNT_ITEM("PEC-003", "item not found"),
    BAD_REQUEST_INPUT_PRODUCT("PEC-004", "Product Input value error"),
    ;


    private final String code;
    private final String message;

    ProductExceptionCode(String code, String message) {
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
