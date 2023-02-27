package com.buying.back.application.account.repository;

import com.buying.back.application.account.controller.dto.management.SearchAccountManagementDTO;
import com.buying.back.application.account.domain.Account;
import com.buying.back.application.account.service.vo.NormalAccountManagementVO;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountRepositoryCustom {

  Page<NormalAccountManagementVO> findAll(Pageable pageable, SearchAccountManagementDTO dto);
  List<Account> findAllBirthDayAccountWithCursor(LocalDate today, long cursor, long limit);
}
