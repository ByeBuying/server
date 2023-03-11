package com.buying.back.application.product.repository;

import com.buying.back.application.product.domain.Option;
import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.service.vo.ItemOptionVO;
import com.buying.back.application.product.service.vo.OptionDefaultVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long>, OptionQueryRepository {
    void deleteByProduct(Product product);

    @Query(value = "select option from Option option where option.id in :ids")
    List<ItemOptionVO> findOptionsByIdIn(@Param("ids") List<Long> ids);
}
