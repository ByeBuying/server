package com.buying.back.application.inquiry.controller.dto.common;

import com.buying.back.application.inquiry.code.type.InquiryChildType;
import com.buying.back.application.inquiry.code.type.InquiryParentType;
import com.buying.back.util.validation.validatedto.ValidateDTO;
import com.buying.back.util.validation.validatedto.inquiry.InquiryDTOValidationCondition;
import com.buying.back.util.verify.VerifyLengthUtil;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.Errors;

@Getter
@Setter
public class UpdateInquiryDTO implements ValidateDTO {

  @NotNull
  private InquiryParentType inquiryParentType;
  @NotNull
  private InquiryChildType inquiryChildType;
  @NotBlank
  @Length(min = 1, max = VerifyLengthUtil.MAX_DEFAULT_LENGTH)
  private String title;
  @NotBlank
  @Length(min = 1, max = VerifyLengthUtil.MAX_DESCRIPTION_LENGTH)
  private String content;

  @Override
  public void validate(Errors e) {
    InquiryDTOValidationCondition.parentChildTypeMatch(
      e, this.inquiryParentType, this.inquiryChildType);
  }
}
