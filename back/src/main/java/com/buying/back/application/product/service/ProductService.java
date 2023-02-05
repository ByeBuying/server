package com.buying.back.application.product.service;

import com.buying.back.application.product.controller.dto.ItemDto;
import com.buying.back.application.product.controller.dto.ProductDto;
import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.repository.ItemRepository;
import com.buying.back.application.product.repository.OptionRepository;
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
    private final ItemRepository itemRepository;
    private final OptionRepository optionRepository;
    private final ProductItemHelperService productItemHelperService;
    private final ProductOptionHelperService productOptionHelperService;

    @Transactional
    public ProductDefaultVO createProduct(ProductDto.Create dto) {
        List<ItemDto.Create> itemsDto = dto.getItemsDto();

        Product product = Product.create(dto);
        productRepository.save(product);

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

        /*List<Item> items = itemsDto.stream().map(itemDto -> {
            StringBuilder itemName = new StringBuilder();
            StringBuilder itemOptions = new StringBuilder();

            List<Option> options = itemDto.getOptionsDto().stream().map(info -> {
                itemName.append(info.getOptionValue())
                        .append("/");
                Option option = Option.create(info);
                option.setProduct(product);
                return option;
            }).collect(Collectors.toList());

            optionRepository.saveAll(options);
            options.forEach(option -> itemOptions.append(option.getId()).append("/"));

            itemDto.setName(itemName.toString());
            itemDto.setOptions(itemOptions.toString());

            Item item = Item.create(itemDto);
            item.setProduct(product);
            return item;
        }).collect(Collectors.toList());
        itemRepository.saveAll(items);*/
        List<OptionDefaultVO> optionDefaultVOList = productOptions.stream().distinct().collect(Collectors.toList());

        return new ProductDefaultVO(product, itemDefaultVOList, optionDefaultVOList);
    }

    @Transactional
    public ProductDefaultVO updateProduct(Long productId, ProductDto.Update dto) {
        // productItemHelperService.updateItems(dto.getItemsDto());
        // TODO: 2023-02-02 나중에 리팩토링
        dto.getItemsDto().forEach(itemDto -> {
            itemRepository.save(
                    itemRepository.findById(itemDto.getItemId())
                            .orElseThrow()
                            .update(itemDto)
            );
        });

        Product product = productRepository.findById(productId).orElseThrow();
        product.update(dto);
        productRepository.save(product);

        return new ProductDefaultVO();
    }

    @Transactional
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("NOT FOND PRODUCT"));
        optionRepository.deleteByProduct(product);
        itemRepository.deleteByProduct(product);
        productRepository.delete(product);
    }
}

