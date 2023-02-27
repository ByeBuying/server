package com.buying.back.application.coupon.controller.dto;

import com.buying.back.application.common.dto.PagingDTO;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Getter
@Setter
public class SearchCouponDTO extends PagingDTO {

  private Boolean activated;
  private Integer expirationPeriod;

}
