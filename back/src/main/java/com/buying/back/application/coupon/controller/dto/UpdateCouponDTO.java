package com.buying.back.application.coupon.controller.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Getter
@Setter
public class UpdateCouponDTO {

  @NotNull
  private Boolean activated;
  @NotNull
  private Integer expirationPeriod;

}
