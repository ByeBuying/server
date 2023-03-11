package com.buying.back.application.product.service.vo;

import com.buying.back.application.product.domain.Option;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemOptionVO extends OptionDefaultVO {
    private String value;

    public ItemOptionVO(Option option) {
        super(option);
        this.value = option.getValue();
    }
}
