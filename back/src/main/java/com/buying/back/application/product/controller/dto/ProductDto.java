package com.buying.back.application.product.controller.dto;

import com.buying.back.util.verify.VerifyLengthUtil;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ProductDto {

    @Data
    public static class Create {
        /*
        * 카테고리는 이미 만들어진 것 ( 따로 API 동작 )
        * Product - Option - Stock
        * */
        @NotBlank
        @Length(min = 1, max = VerifyLengthUtil.MAX_NAME_LENGTH)
        private String name;
        @NotNull
        @Min(value = 0, message = "상품 가격은 0 보다 커야합니다.")
        private Integer price;
        @NotNull
        private Long brandId;
        @NotNull
        private Long categoryId;
        @Valid
        @NotNull(message = "Item 은 1개 이상 등록되어야 합니다.(Item List is Null)")
        @Size(min = 1, message = "Item 은 1개 이상 등록되어야 합니다.(Item List size[0])")
        private List<ItemDto.Create> itemsDto;
    }

    @Data
    public static class Update {
        @NotBlank
        @Length(min = 1, max = VerifyLengthUtil.MAX_NAME_LENGTH)
        private String name;
        @NotNull
        private Long categoryId;
        @NotBlank
        @Min(value = 0, message = "상품 가격은 0 보다 커야합니다.")
        private Integer price;
        @Valid
        private List<ItemDto.Update> itemsDto;
        @Valid
        private List<ItemDto.Create> newItemsDto;
    }
}
