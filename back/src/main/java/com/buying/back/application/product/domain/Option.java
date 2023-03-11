package com.buying.back.application.product.domain;

import com.buying.back.application.product.controller.dto.OptionDto;
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
public class Option {
    @Id @Column(name = "option_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String value;
    private Integer orderBy;
    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Builder
    private Option(String name, String value, Integer orderBy, Product product) {
        this.name = name;
        this.value = value;
        this.orderBy = orderBy;
        this.product = product;
    }

    public static Option create(OptionDto.Create dto) {
        return Option.builder()
                .name(dto.getOptionName())
                .value(dto.getOptionValue())
                .orderBy(dto.getOrderBy())
                .build();
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
