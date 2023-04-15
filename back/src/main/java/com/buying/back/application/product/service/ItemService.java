package com.buying.back.application.product.service;

import com.buying.back.application.product.controller.dto.ItemDto;
import com.buying.back.application.product.domain.Item;
import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.repository.ItemRepository;
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

    public List<ItemDefaultVO> getAllByProduct(Product product) {
        List<Item> items = itemRepository.findAllByProduct(product);
        List<ItemDefaultVO> vos = items.stream().map(ItemDefaultVO::valueOf).collect(Collectors.toList());
        return vos;
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

    @Transactional
    public ItemDefaultVO updateItem(ItemDto.Update itemDto) {
        Item item = itemRepository.findById(itemDto.getItemId()).orElseThrow(() -> new RuntimeException("NOT FOUND ITEM"));
        item.update(itemDto);
        itemRepository.save(item);

        return new ItemDefaultVO(item);
    }

    @Transactional
    public void deleteByProduct(Product product) {
        itemRepository.deleteByProduct(product);
    }
}
