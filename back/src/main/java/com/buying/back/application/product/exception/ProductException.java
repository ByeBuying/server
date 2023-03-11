package com.buying.back.application.product.exception;

import com.buying.back.application.common.exception.CommonException;
import com.buying.back.util.response.ResponseCode;

public class ProductException extends CommonException {
    public ProductException(ResponseCode responseCode) {
        super(responseCode);
    }

    public ProductException(ResponseCode responseCode, String detailMessage) {
        super(responseCode, detailMessage);
    }
}
