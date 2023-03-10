package com.buying.back.application.inquiry.controller;

import static com.buying.back.util.response.CommonResponseCode.SUCCESS;

import com.buying.back.application.inquiry.controller.dto.common.CreateInquiryDTO;
import com.buying.back.application.inquiry.controller.dto.normal.SearchInquiryNormalDTO;
import com.buying.back.application.inquiry.controller.dto.common.UpdateInquiryDTO;
import com.buying.back.application.inquiry.service.InquiryService;
import com.buying.back.application.inquiry.service.vo.InquiryDetailVO;
import com.buying.back.application.inquiry.service.vo.InquiryVO;
import com.buying.back.infra.config.security.loginuser.LoginUser;
import com.buying.back.util.response.CommonResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pub/inquiries")
@RequiredArgsConstructor
public class InquiryNormalController {

  private final InquiryService inquiryService;

  @PostMapping
  public CommonResponse<InquiryDetailVO> createNormalInquiry(@AuthenticationPrincipal LoginUser loginUser,
    @Valid @RequestBody CreateInquiryDTO dto) {
    InquiryDetailVO vo = inquiryService.createNormalInquiry(loginUser.getAccountId(), dto);
    return new CommonResponse<>(vo, SUCCESS);
  }

  @GetMapping
  public CommonResponse<Page<InquiryVO>> getMyInquiryList(
    @AuthenticationPrincipal LoginUser loginUser,
    @Valid SearchInquiryNormalDTO dto) {
    Page<InquiryVO> vo = inquiryService.getMyInquiryList(loginUser.getAccountId(), dto);
    return new CommonResponse<>(vo, SUCCESS);
  }

  @GetMapping("/{inquiry-id}")
  public CommonResponse<InquiryDetailVO> getMyInquiryDetail(
    @AuthenticationPrincipal LoginUser loginUser,
    @PathVariable(value = "inquiry-id") Long inquiryId) {
    InquiryDetailVO vo = inquiryService.getMyInquiryDetail(loginUser.getAccountId(), inquiryId);
    return new CommonResponse<>(vo, SUCCESS);
  }

  @PutMapping("/{inquiry-id}")
  public CommonResponse<InquiryDetailVO> updateMyInquiry(@AuthenticationPrincipal LoginUser loginUser,
    @PathVariable(value = "inquiry-id") Long inquiryId,
    @Valid @RequestBody UpdateInquiryDTO dto) {
    InquiryDetailVO vo = inquiryService.updateMyInquiry(loginUser.getAccountId(), inquiryId, dto);
    return new CommonResponse<>(vo, SUCCESS);
  }

  @PutMapping("/{inquiry-id}:delete")
  public CommonResponse<Void> deleteMyInquiry(@AuthenticationPrincipal LoginUser loginUser,
    @PathVariable(value = "inquiry-id") Long inquiryId) {
    inquiryService.deleteMyInquiry(loginUser.getAccountId(), inquiryId);
    return new CommonResponse<>(SUCCESS);
  }

}
