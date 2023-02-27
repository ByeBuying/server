package com.buying.back.infra.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static com.buying.back.application.account.code.type.RoleType.*;

@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

  public static final String SECURITY_LOG_IN_URL = "/api/login";
  public static final String SECURITY_LOG_OUT_URL = "/api/logout";
  private final CustomAuthenticationProvider authenticationProvider;
  private final RestAuthenticationEntryPoint authenticationEntryPoint;
  private final RestSuccessHandler restSuccessHandler;
  private final RestFailureHandler restFailureHandler;
  private final RestAccessDeniedHandler restAccessDeniedHandler;
  private final LogoutSuccessHandler logoutSuccessHandler;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  public GrantedAuthorityDefaults grantedAuthorityDefaults() {
    return new GrantedAuthorityDefaults("");
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return web -> web
      .ignoring().antMatchers("/profile", "/manage/health", "/h2-console/**",
        "/v3/api-docs", "/swagger-ui/**", "/swagger-resources/**", "/swagger-ui.html",
        "/webjars/**", "/swagger/**");
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .cors().disable()
      .csrf().disable()
      .formLogin().disable()
      .exceptionHandling()
      .authenticationEntryPoint(authenticationEntryPoint)
      .accessDeniedHandler(restAccessDeniedHandler)
      .and()
      .logout()
      .logoutRequestMatcher(new AntPathRequestMatcher(SECURITY_LOG_OUT_URL))
      .logoutSuccessHandler(logoutSuccessHandler)
      .invalidateHttpSession(true); // 로그아웃 이후 세션 전제 삭제 여부

    http
      .authorizeRequests()
      .antMatchers("/api/v1/sys/**").hasRole(SYSTEM.getValue())
      .antMatchers("/api/v1/pub/**").hasAnyRole(NORMAL.getValue(), SYSTEM.getValue())
      .antMatchers("/api/v1/brd/**").hasAnyRole(
        BRAND_ADMIN.getValue(), BRAND_CREW.getValue())
      .antMatchers(SECURITY_LOG_OUT_URL).authenticated()
      .antMatchers(SECURITY_LOG_IN_URL, "/api/v1/auth/**").permitAll()
      .anyRequest().denyAll();

    http
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // 세션 정책, IF_REQUIRED: 필요 시 생성
      .maximumSessions(1) // 최대 허용 가능 세션 수를 설정 (값으로 -1을 넣으면 무제한으로 세션 생성 가능)
      .maxSessionsPreventsLogin(true); // 최대 허용 세션 수일 때 추가적인 인증 요청이 있을 경우 어떻게 처리할지
                                       // true: 현재 사용자 인증 실패, false: 기존 세션 만료

    AuthenticationManager authenticationManager =
      authenticationManager(http.getSharedObject(AuthenticationConfiguration.class));
    CustomUsernamePasswordAuthenticationFilter authenticationFilter =
      getAuthenticationFilter(authenticationManager);

    AuthenticationManagerBuilder authenticationManagerBuilder =
      http.getSharedObject(AuthenticationManagerBuilder.class);
    authenticationManagerBuilder.authenticationProvider(authenticationProvider);

    http
      .addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(
    AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public CustomUsernamePasswordAuthenticationFilter getAuthenticationFilter(
    AuthenticationManager authenticationManager) {
    CustomUsernamePasswordAuthenticationFilter filter =
      new CustomUsernamePasswordAuthenticationFilter();
    try {
      filter.setFilterProcessesUrl(SECURITY_LOG_IN_URL);
      filter.setUsernameParameter("email");
      filter.setPasswordParameter("password");
      filter.setAuthenticationManager(authenticationManager);
      filter.setAuthenticationSuccessHandler(restSuccessHandler);
      filter.setAuthenticationFailureHandler(restFailureHandler);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return filter;
  }

  @Profile({"local", "dev"})
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    log.info("security cors config");
    CorsConfiguration configuration = new CorsConfiguration();

    configuration.addAllowedOrigin("*");
    configuration.addAllowedHeader("*");
    configuration.addAllowedMethod("*");
    configuration.setAllowCredentials(false);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

}
