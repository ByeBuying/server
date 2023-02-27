//package com.buying.back.infra.config.webconfig;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Slf4j
//@Configuration
//@Profile({"local", "dev"})
//public class CorsConfig {
//
//  @Bean
//  public WebMvcConfigurer corsConfigurer() {
//    log.info("Cors Config");
//    return new WebMvcConfigurer() {
//
//      @Override
//      public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//          .allowedMethods("*")
//          .allowedHeaders("*")
//          .allowedOrigins("*")
//          .allowCredentials(false)
//          .maxAge(3000);
//      }
//    };
//  }
//}
