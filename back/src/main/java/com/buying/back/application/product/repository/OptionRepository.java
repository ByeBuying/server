package com.buying.back.application.product.repository;

import com.buying.back.application.product.domain.Option;
import com.buying.back.application.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long>, OptionRepositoryCustom {
    void deleteByProduct(Product product);
}
