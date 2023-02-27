package com.buying.back.infra.config.webconfig;

import com.buying.back.infra.config.webconfig.inquiry.converter.InquiryChildTypeConverter;
import com.buying.back.infra.config.webconfig.inquiry.converter.InquiryParentTypeConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addFormatters(FormatterRegistry registry) {
    WebMvcConfigurer.super.addFormatters(registry);
    registry.addConverter(new InquiryParentTypeConverter());
    registry.addConverter(new InquiryChildTypeConverter());
  }
}
