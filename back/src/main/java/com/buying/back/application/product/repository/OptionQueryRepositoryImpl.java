package com.buying.back.application.product.repository;

import com.buying.back.application.product.domain.Option;
import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.service.vo.OptionDefaultVO;
import com.buying.back.application.product.service.vo.QOptionDefaultVO;
import com.buying.back.util.querydsl.CustomQuerydslRepositorySupport;

import java.util.List;

import static com.buying.back.application.product.domain.QOption.option;

public class OptionQueryRepositoryImpl extends CustomQuerydslRepositorySupport implements OptionQueryRepository {
    public OptionQueryRepositoryImpl() {
        super(Option.class);
    }

    public List<OptionDefaultVO> findDistinctNameByProduct(Product product) {
        return select(getOptionDefaultVO())
                .distinct()
                .from(option)
                .where(option.product.eq(product))
                .orderBy(option.orderBy.asc())
                .fetch();
    }

    private QOptionDefaultVO getOptionDefaultVO() {
        return new QOptionDefaultVO(
                option.id,
                option.name,
                option.orderBy
        );
    }
}
