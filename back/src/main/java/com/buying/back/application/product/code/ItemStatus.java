package com.buying.back.application.product.code;

public enum ItemStatus {
    SALE("판매"),
    SALES_END("판매종료"),
    DEADLINE("품절임박"),
    SOLD_OUT("품절"),
    ;

    private final String status;

    ItemStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
