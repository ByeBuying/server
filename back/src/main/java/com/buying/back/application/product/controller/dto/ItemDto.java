package com.buying.back.application.product.controller.dto;

import com.buying.back.util.verify.VerifyLengthUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ItemDto {

    @Data
    public static class Create {
        @NotNull(message = "상품명은 필수 값입니다.")
        @Length(min = 1, max = VerifyLengthUtil.MAX_NAME_LENGTH)
        private String name;
        @JsonIgnore
        private String options;
        @NotNull
        @Min(value = 0, message = "Item 재고 수량은 0 보다 작을 수 없습니다.")
        private Integer quantity;
        @NotNull
        @Min(value = 0, message = "가격은 0 보다 커야합니다.")
        private Integer price;
        @Min(value = 0)
        private Integer discountPrice;
        @Min(value = 0)
        private Integer discountRate;
        @Valid
        private List<OptionDto.Create> optionsDto;
    }

    @Data
    public static class Update {
        @NotNull
        private Long itemId;
        @NotBlank
        @Length(min = 1, max = VerifyLengthUtil.MAX_NAME_LENGTH)
        private String name;
        @NotNull
        @Min(value = 0, message = "Item 재고 수량은 0 보다 작을 수 없습니다.")
        private Integer quantity;
        @NotNull
        @Min(value = 0, message = "가격은 0 보다 커야합니다.")
        private Integer price;
        @Min(value = 0)
        private Integer discountPrice;
        @Min(value = 0)
        private Integer discountRate;
        @Valid
        private List<OptionDto.Create> optionsDto;
    }
}
