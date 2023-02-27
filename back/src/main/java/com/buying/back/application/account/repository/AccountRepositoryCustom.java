package com.buying.back.application.account.repository;

import com.buying.back.application.account.controller.dto.management.SearchAccountManagementDTO;
import com.buying.back.application.account.service.vo.NormalAccountManagementVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountRepositoryCustom {

  Page<NormalAccountManagementVO> findAll(Pageable pageable, SearchAccountManagementDTO dto);
}
