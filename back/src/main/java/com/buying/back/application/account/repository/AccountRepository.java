package com.buying.back.application.account.repository;

import com.buying.back.application.account.domain.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, Long>,
  AccountRepositoryCustom {

  @Query(value = "SELECT A.* from ACCOUNT A WHERE BINARY(A.EMAIL) = :email", nativeQuery = true)
  Optional<Account> findByBinaryEmail(@Param("email") String email);

  Optional<Account> findByEmail(String email);


}
