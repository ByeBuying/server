package com.buying.back.application.product.service.vo;

import com.buying.back.application.product.domain.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductDefaultVO {
    private Long productId;
    private String name;
    private Integer price;
    // TODO: 2023-02-02 Category Response Add
    // TODO: 2023-02-02 Brand Response  Add
    private List<ItemDefaultVO> items;
    private List<OptionDefaultVO> options;

    public ProductDefaultVO(Product product, List<ItemDefaultVO> items, List<OptionDefaultVO> options) {
        this.productId = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.items = items;
        this.options = options;
    }

    public void add(ItemDefaultVO itemDefaultVO) {
        items.add(itemDefaultVO);
    }
}
