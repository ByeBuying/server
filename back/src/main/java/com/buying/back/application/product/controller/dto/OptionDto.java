package com.buying.back.application.product.controller.dto;

import com.buying.back.util.verify.VerifyLengthUtil;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OptionDto {
    @Data
    public static class Create {
        @NotBlank
        @Length(min = 1, max = VerifyLengthUtil.MAX_NAME_LENGTH)
        private String optionName;
        @NotBlank
        @Length(min = 1, max = VerifyLengthUtil.MAX_NAME_LENGTH)
        private String optionValue;
        @NotNull
        @Min(0)
        private Integer orderBy;
    }

    @Data
    public static class Update {
        @NotNull
        private Long optionId;
        @NotBlank
        @Length(min = 1, max = VerifyLengthUtil.MAX_NAME_LENGTH)
        private String optionName;
        @NotBlank
        @Length(min = 1, max = VerifyLengthUtil.MAX_NAME_LENGTH)
        private String optionValue;
        @NotBlank
        private String orderBy;
    }
}
