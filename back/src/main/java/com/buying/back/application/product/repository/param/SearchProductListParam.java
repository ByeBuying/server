package com.buying.back.application.product.repository.param;

import com.buying.back.application.product.controller.dto.ProductDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SearchProductListParam {

  private String productName;

  private Long categoryId;
  private Long brandId;

  private Integer priceFrom;
  private Integer priceTo;

  private LocalDate createdDateFrom;
  private LocalDate createdDateTo;

  private Boolean deleted;

  public static SearchProductListParam valueOf(ProductDto.Search dto) {
    SearchProductListParam param = new SearchProductListParam();
    param.setProductName(dto.getProductName());
    param.setCategoryId(dto.getCategoryId());
    param.setPriceFrom(dto.getPriceFrom());
    param.setPriceTo(dto.getPriceTo());
    param.setCreatedDateFrom(dto.getCreatedDateFrom());
    param.setCreatedDateTo(dto.getCreatedDateTo());
    return param;
  }
}
