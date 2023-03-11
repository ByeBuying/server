package com.buying.back.application.product.service;

import com.buying.back.application.product.controller.dto.ItemDto;
import com.buying.back.application.product.domain.Item;
import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.repository.ItemRepository;
import com.buying.back.application.product.repository.ProductRepository;
import com.buying.back.application.product.service.vo.ItemDefaultVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public List<Item> getAllByProduct(Product product) {
        return itemRepository.findAllByProductId(product);
    }

    public List<ItemDefaultVO> getItemsByProduct(Product product) {
        return itemRepository.findItemsByProductId(product);
    }
    @Transactional
    public Item createItem(Product product, ItemDto.Create dto) {
        Item item = Item.create(dto);
        item.setProduct(product);
        return itemRepository.save(item);
    }

    @Transactional
    public List<Item> createItemAll(Product product, List<ItemDto.Create> itemsDto) {
        return itemRepository.saveAll(itemsDto.stream().map(Item::create).collect(Collectors.toList()));
    }


    public Item updateItems(ItemDto.Update itemDto) {
        // TODO: 2023-02-02 updateItems 작성
        return itemRepository.save(
                    itemRepository.findById(itemDto.getItemId()).orElseThrow(() -> new RuntimeException("NOT FOUND ITEM"))
                        .update(itemDto)
                );
    }

    public void deleteByProduct(Product product) {
        itemRepository.deleteByProduct(product);
    }
}
