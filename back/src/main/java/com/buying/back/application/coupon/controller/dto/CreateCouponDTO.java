package com.buying.back.application.coupon.controller.dto;

import com.buying.back.util.verify.VerifyLengthUtil;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Getter
@Setter
public class CreateCouponDTO {

  @NotBlank
  @Length(min = 1, max = VerifyLengthUtil.MAX_DEFAULT_LENGTH)
  private String name;
  @NotNull
  @DecimalMin(value = "0.0", inclusive = false) // 0.0 보다 커야 함(inclusive = false)
  @Digits(integer = 19, fraction = 0) // 정수 19 자리까지 가능하며 소수점은 0자리
  private BigDecimal discountPercent;
  @NotNull
  @DecimalMin(value = "0.0", inclusive = false)
  @Digits(integer = 19, fraction = 0)
  private BigDecimal orderMinimumAmount;
  @NotNull
  private Integer expirationPeriod;
}
