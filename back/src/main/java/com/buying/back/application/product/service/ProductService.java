package com.buying.back.application.product.service;

import com.buying.back.application.product.code.ProductExceptionCode;
import com.buying.back.application.product.controller.dto.ItemDto;
import com.buying.back.application.product.controller.dto.ProductDto;
import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.exception.ProductException;
import com.buying.back.application.product.repository.ProductRepository;
import com.buying.back.application.product.service.vo.ItemDefaultVO;
import com.buying.back.application.product.service.vo.ItemOptionVO;
import com.buying.back.application.product.service.vo.OptionDefaultVO;
import com.buying.back.application.product.service.vo.ProductDefaultVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductItemHelperService productItemHelperService;
    private final ProductOptionHelperService productOptionHelperService;

    public ProductDefaultVO getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException(ProductExceptionCode.NOT_FOUND_PRODUCT));

        List<ItemDefaultVO> itemsByProduct = productItemHelperService.getItemsByProduct(product);
        List<OptionDefaultVO> productOptions = productOptionHelperService.getProductOptions(product);

        return new ProductDefaultVO(product, itemsByProduct, productOptions);
    }

    @Transactional
    public ProductDefaultVO createProduct(ProductDto.Create dto) {
        List<ItemDto.Create> itemsDto = dto.getItemsDto();

        Product product = Product.create(dto);
        productRepository.save(product);

        return createItemsAndOptions(product, itemsDto);
    }

    @Transactional
    public ProductDefaultVO updateProduct(Long productId, ProductDto.Update dto) {
        Product product = productRepository.findById(productId).orElseThrow();
        product.update(dto);
        productRepository.save(product);

        List<ItemDto.Create> newItemsDto = dto.getNewItemsDto();
        if(!newItemsDto.isEmpty()) {
            createItemsAndOptions(product, newItemsDto);
        }
        dto.getItemsDto().forEach(productItemHelperService::updateItems);

        List<ItemDefaultVO> itemsByProduct = productItemHelperService.getItemsByProduct(product);
        List<OptionDefaultVO> productOptions = productOptionHelperService.getProductOptions(product);
        return new ProductDefaultVO(product, itemsByProduct, productOptions);
    }

    @Transactional
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("NOT FOND PRODUCT"));
        productItemHelperService.deleteItemByProduct(product);
        productOptionHelperService.deleteOptionByProduct(product);
        productRepository.delete(product);
    }

    private ProductDefaultVO createItemsAndOptions(Product product, List<ItemDto.Create> itemsDto) {
        List<OptionDefaultVO> productOptions = new ArrayList<>();
        List<ItemDefaultVO> itemDefaultVOList = itemsDto.stream().map(itemDto -> {
            StringBuilder itemOptions = new StringBuilder();

            List<ItemOptionVO> itemOptionsVO = productOptionHelperService.createOptionAll(product, itemDto.getOptionsDto()).stream()
                    .map(item -> {
                        ItemOptionVO itemOptionVO = new ItemOptionVO(item);
                        productOptions.add(itemOptionVO);
                        return itemOptionVO;
                    })
                    .collect(Collectors.toList());

            for (int i = 0; i < itemOptionsVO.size(); i++) {
                itemOptions.append(itemOptionsVO.get(i).getId());
                if (itemOptionsVO.size() - 1 != i) {
                    itemOptions.append("/");
                }
            }
            itemDto.setOptions(itemOptions.toString());
            return new ItemDefaultVO(productItemHelperService.createItem(product, itemDto), itemOptionsVO);
        }).collect(Collectors.toList());

        List<OptionDefaultVO> optionDefaultVOList = productOptions.stream().distinct().collect(Collectors.toList());

        return new ProductDefaultVO(product, itemDefaultVOList, optionDefaultVOList);
    }
}

