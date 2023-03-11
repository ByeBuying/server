package com.buying.back.application.product.service.vo;

import com.buying.back.application.product.domain.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ItemDefaultVO {
    private Long id;
    private String name;
    private Integer price;
    private Integer quantity;
    private Integer discountPrice;
    private Integer discountRate;
    private List<ItemOptionVO> options;

    public ItemDefaultVO(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();
        this.discountPrice = item.getDiscountPrice();
        this.discountRate = item.getDiscountRate();
    }

    public ItemDefaultVO(Item item, List<ItemOptionVO> options) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();
        this.discountPrice = item.getDiscountPrice();
        this.discountRate = item.getDiscountRate();
        this.options = options;
    }

    public ItemDefaultVO(List<ItemOptionVO> options) {
        this.options = options;
    }
}
