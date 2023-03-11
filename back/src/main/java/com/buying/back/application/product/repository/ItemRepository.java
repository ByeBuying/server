package com.buying.back.application.product.repository;

import com.buying.back.application.product.domain.Item;
import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.service.vo.ItemDefaultVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    void deleteByProduct(Product product);

    @Query(value = "select item from Item item where item.product = :product")
    List<ItemDefaultVO> findItemsByProductId(@Param("product") Product product);

    @Query(value = "select item From Item item where item.product = :product")
    List<Item> findAllByProductId(@Param("product") Product product);
}