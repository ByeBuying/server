package com.buying.back.application.inquiry.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.buying.back.application.common.dto.PagingDTO;
import com.buying.back.application.inquiry.service.vo.InquiryVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class InquiryRepositoryTest {

  @Autowired
  private InquiryRepository inquiryRepository;

  @Test
  void findAllByAccount() {
    PagingDTO dto = new PagingDTO();
    Long accountId = 1L;

    Page<InquiryVO> page = inquiryRepository.findAllByAccount(dto.getPageRequest(), accountId);
    /*
    select
      account1_.account_id as col_0_0_,
      account1_.name as col_1_0_,
      inquiry0_.inquiry_id as col_2_0_,
      inquiry0_.created_date_time as col_3_0_,
      inquiry0_.updated_date_time as col_4_0_,
      inquiry0_.title as col_5_0_,
      inquiry0_.content as col_6_0_,
      inquiry0_.inquiry_parent_type as col_7_0_,
      inquiry0_.inquiry_child_type as col_8_0_
    from
      inquiry inquiry0_
    inner join
      account account1_
        on inquiry0_.inquiry_author_id=account1_.account_id
    where
      inquiry0_.inquiry_author_id=?
    order by
      inquiry0_.inquiry_id desc limit ?
     */
    assertNotNull(page);
  }

}
