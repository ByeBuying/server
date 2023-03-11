package com.buying.back.application.product.controller.dto;

import lombok.Data;
import lombok.Getter;

public class CategoryDto {

    @Data
    public static class Create {
        private String name;
        private Long parentId;
        public Create(String name, Long parentId) {
            this.name = name;
            this.parentId = parentId;
        }
    }
}
