package com.buying.back.application.product.service.vo;

import com.buying.back.application.product.domain.Option;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OptionDefaultVO {
    private Long id;
    private String name;
    private Integer orderBy;

    @QueryProjection
    public OptionDefaultVO(Long id, String name, Integer orderBy) {
        this.id = id;
        this.name = name;
        this.orderBy = orderBy;
    }

    public OptionDefaultVO(Option option) {
        this(option.getId(), option.getName(), option.getOrderBy());
    }
}
