package com.buying.back.application.quartz.service;

import com.buying.back.application.account.domain.Account;
import com.buying.back.application.account.helper.AccountCouponHelper;
import com.buying.back.application.account.repository.AccountRepository;
import com.buying.back.application.coupon.code.exception.CouponException;
import com.buying.back.application.coupon.code.exception.CouponException.CouponExceptionCode;
import com.buying.back.application.coupon.domain.Coupon;
import com.buying.back.application.coupon.repository.CouponRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BatchService {

  private final AccountRepository accountRepository;
  private final CouponRepository couponRepository;
  private final AccountCouponHelper accountCouponHelper;
  private final int TRANSACTION_CHUNK_SIZE = 30; // 작업할 데이터의 각 커밋 사이에 처리되는 row 수
  // Chunk 지향 정리 -> 한번에 읽어오는 Chunk라는 덩어리를 만든 뒤 Chunk 단위로 트랜잭션을 다루는 것.
  // 이렇게 하면 Chunk 단위로 작업이 실행되고 실패했을 경우 그만큼 롤백되어 이전 커밋된 Chunk 들은 반영이 된다는 것

  @Transactional
  public void batchCheckAccountBirthDay() {
    long cursor = 0L;
    long accountListSize;

    LocalDate today = LocalDate.now();
    String couponSuffix = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    Coupon birthDayCoupon = couponRepository.findByNameStartsWith(couponSuffix.concat("_생일"))
      .orElseThrow(() -> new CouponException(CouponExceptionCode.NOT_FOUND_COUPON));

    do {
      List<Account> birthDayAccounts = accountRepository
        .findAllBirthDayAccountWithCursor(today, cursor, TRANSACTION_CHUNK_SIZE);
      accountListSize = birthDayAccounts.size();

      for (Account account: birthDayAccounts) {
        accountCouponHelper.assignCoupon(account.getId(), birthDayCoupon.getId());
        cursor = account.getId();
      }
    } while(accountListSize == TRANSACTION_CHUNK_SIZE);
  }

}
