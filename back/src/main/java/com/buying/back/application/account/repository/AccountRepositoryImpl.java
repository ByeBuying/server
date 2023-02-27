package com.buying.back.application.account.repository;

import static com.buying.back.application.account.domain.QAccount.account;

import com.buying.back.application.account.controller.dto.management.SearchAccountManagementDTO;
import com.buying.back.application.account.domain.Account;
import com.buying.back.application.account.service.vo.NormalAccountManagementVO;
import com.buying.back.application.account.service.vo.QNormalAccountManagementVO;
import com.buying.back.util.querydsl.CustomQuerydslRepositorySupport;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import java.util.Objects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class AccountRepositoryImpl extends CustomQuerydslRepositorySupport
  implements AccountRepositoryCustom {

  public AccountRepositoryImpl() {
    super(Account.class);
  }

  @Override
  public Page<NormalAccountManagementVO> findAll(Pageable pageable, SearchAccountManagementDTO dto) {
    BooleanBuilder whereCondition = getAccountWhereCondition(dto);

    JPAQuery<NormalAccountManagementVO> query = select(getAccountManagementVO())
      .from(account)
      .where(whereCondition)
      .orderBy(account.id.asc());

    JPAQuery<Long> countQuery = select(account.count())
      .from(account)
      .where(whereCondition);

    return applyPagination(pageable, query, countQuery);
  }

  private QNormalAccountManagementVO getAccountManagementVO() {
    return new QNormalAccountManagementVO(
      account.id,
      account.email,
      account.name,
      account.birthDay,
      account.roleType,
      account.gradeType,
      account.activated,
      account.signUpDateTime,
      account.recentSignInDateTime
    );
  }

  private BooleanBuilder getAccountWhereCondition(SearchAccountManagementDTO dto) {
    BooleanBuilder whereCondition = new BooleanBuilder();

    if (Objects.nonNull(dto.getActivated())) {
      whereCondition.and(account.activated.eq(dto.getActivated()));
    }

    if (Objects.nonNull(dto.getRoleType())) {
      whereCondition.and(account.roleType.eq(dto.getRoleType()));
    }

    if (Objects.nonNull(dto.getAccountGradeType())) {
      whereCondition.and(account.gradeType.eq(dto.getAccountGradeType()));
    }

    return whereCondition;
  }
}
