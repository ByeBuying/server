package com.buying.back.application.product.repository;

import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.service.vo.OptionDefaultVO;

import java.util.List;

public interface OptionQueryRepository {
    List<OptionDefaultVO> findDistinctNameByProduct(Product product);
}
