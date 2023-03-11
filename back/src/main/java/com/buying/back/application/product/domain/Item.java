package com.buying.back.application.product.domain;

import com.buying.back.application.product.controller.dto.ItemDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Item {
    @Id @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String options;
    private Integer quantity;
    private Integer price;
    private Integer discountPrice;
    private Integer discountRate;

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Builder
    private Item(String name, String options, Integer quantity, Integer price, Integer discountPrice, Integer discountRate, Product product) {
        this.name = name;
        this.options = options;
        this.quantity = quantity;
        this.price = price;
        this.discountPrice = discountPrice;
        this.discountRate = discountRate;
        this.product = product;
    }

    public static Item create(ItemDto.Create dto) {
        return Item.builder()
                .name(dto.getName())
                .options(dto.getOptions())
                .quantity(dto.getQuantity())
                .price(dto.getPrice())
                .discountPrice(dto.getDiscountPrice())
                .discountRate(dto.getDiscountRate())
                .build();
    }

    public Item update(ItemDto.Update dto) {
        this.name = dto.getName();
        this.price = dto.getPrice();
        this.quantity = dto.getQuantity();
        this.discountPrice = dto.getDiscountPrice();
        this.discountRate = dto.getDiscountRate();
        return this;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
