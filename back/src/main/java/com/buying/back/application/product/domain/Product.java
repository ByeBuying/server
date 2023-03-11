package com.buying.back.application.product.domain;

import com.buying.back.application.common.domain.Base;
import com.buying.back.application.product.controller.dto.ProductDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        indexes = {
                @Index(columnList = "name"),
                @Index(columnList = "categoryId"),
                @Index(columnList = "brandId")
        }
)
@Entity
public class Product extends Base {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long categoryId;
    private Long brandId;
    private String name;
    private Integer price;
    @Builder
    private Product(Long categoryId, Long brandId, String name, Integer price) {
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.name = name;
        this.price = price;
    }

    public static Product create(ProductDto.Create dto) {
        return Product.builder()
                .name(dto.getName())
                .brandId(dto.getBrandId())
                .categoryId(dto.getCategoryId())
                .price(dto.getPrice())
                .build();
    }

    public void update(ProductDto.Update dto) {
        this.name = dto.getName();
        this.price = dto.getPrice();
        this.categoryId = dto.getCategoryId();
    }
}
