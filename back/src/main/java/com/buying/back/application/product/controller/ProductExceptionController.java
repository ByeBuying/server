package com.buying.back.application.product.controller;

import com.buying.back.application.product.code.ProductExceptionCode;
import com.buying.back.util.response.CommonResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = ProductController.class)
public class ProductExceptionController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResponse<String> validError(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();

        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append("[");
            builder.append(fieldError.getField());
            builder.append("](은)는 ");
            builder.append(fieldError.getDefaultMessage());
            builder.append(" 입력된 값: [");
            builder.append(fieldError.getRejectedValue());
            builder.append("]");
        }

        return new CommonResponse<>(builder.toString(), ProductExceptionCode.BAD_REQUEST_INPUT_PRODUCT);
    }
}
