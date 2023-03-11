package com.buying.back.application.product.controller;

import com.buying.back.application.product.controller.dto.ProductDto;
import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.service.ProductService;
import com.buying.back.application.product.service.vo.ProductDefaultVO;
import com.buying.back.util.response.CommonResponse;
import com.buying.back.util.response.CommonResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1/pub/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "상품 단건 조회", description = "상품에 대한 각 옵션과 각 옵션별 아이템들을 조회합니다.")
    @GetMapping("/{product_id}")
    public CommonResponse<ProductDefaultVO> getProduct(@PathVariable("product_id") Long productId) {
        ProductDefaultVO productVO = productService.getProduct(productId);
        return new CommonResponse<>(productVO, CommonResponseCode.SUCCESS);
    }

    @Operation(summary = "상품 등록", description = "상품에 대한 옵션과 각 옵션별 아이템들을 등록합니다.")
    @PostMapping
    public CommonResponse<ProductDefaultVO> createProduct(@RequestBody @Valid ProductDto.Create dto) {
        ProductDefaultVO product = productService.createProduct(dto);
        return new CommonResponse<>(product, CommonResponseCode.SUCCESS);
    }

    @Operation(summary = "상품 수정", description = "상품 정보 수정, 옵션 수정 불가")
    @PatchMapping("/{product_id}")
    public CommonResponse<ProductDefaultVO> updateProduct(@PathVariable("product_id") Long productId, @RequestBody @Valid ProductDto.Update dto) {
        /*
         * 카테고리는 수정 가능
         * 브랜드는 수정 불가능
         * 기존 옵션에 옵션값 추가만 가능
         * 아이템은 재고, 가격, 할인가, 할인율, 상품상태(추가예정) 만 가능하게
         * 옵션은 수정 불가
         */
        ProductDefaultVO productDefaultVO = productService.updateProduct(productId, dto);
        return new CommonResponse<>(productDefaultVO, CommonResponseCode.SUCCESS);
    }

    @Operation(summary = "상품 삭제", description = "상품에 대한 옵션들과 각 옵션별 아이템들을 삭제합니다.")
    @DeleteMapping("/{product_id}")
    public CommonResponse<Product> deleteProduct(@PathVariable("product_id") Long productId) {
        productService.deleteProduct(productId);

        return new CommonResponse<>(null, CommonResponseCode.SUCCESS);
    }
}
