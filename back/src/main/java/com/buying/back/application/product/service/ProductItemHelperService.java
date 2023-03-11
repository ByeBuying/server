package com.buying.back.application.product.service;

import com.buying.back.application.product.controller.dto.ItemDto;
import com.buying.back.application.product.domain.Item;
import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.service.vo.ItemDefaultVO;
import com.buying.back.application.product.service.vo.ItemOptionVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductItemHelperService {
    private final OptionService optionService;
    private final ItemService itemService;

    public List<ItemDefaultVO> getItemsByProduct(Product product) {
        return itemService.getAllByProduct(product).stream().map(item -> {
            List<Long> optionIds = Arrays.stream(item.getOptions().split("/")).map(Long::valueOf).collect(Collectors.toList());
            List<ItemOptionVO> itemOptions = optionService.getItemOptions(optionIds);
            return new ItemDefaultVO(item, itemOptions);
        }).collect(Collectors.toList());
    }

    public Item createItem(Product product, ItemDto.Create dto) {
        return itemService.createItem(product, dto);
    }

    public Item updateItems(ItemDto.Update itemDto) {
        return itemService.updateItems(itemDto);
    }

    public void deleteItemByProduct(Product product) {
        itemService.deleteByProduct(product);
    }
}
