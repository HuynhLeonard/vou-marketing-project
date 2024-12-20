package com.voufinal.event.service;

import com.voufinal.event.client.UserServiceClient;
import com.voufinal.event.dto.BrandDto;
import com.voufinal.event.dto.ItemDto;
import com.voufinal.event.dto.ReturnItemDto;
import com.voufinal.event.entity.*;
import com.voufinal.event.mapper.BrandMapper;
import com.voufinal.event.mapper.ItemMapper;
import com.voufinal.event.repository.*;
import com.voufinal.event.exception.NotFoundException;
import com.voufinal.event.dto.VoucherItemDto;
import com.voufinal.event.mapper.VoucherItemMapper;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

@Service
@AllArgsConstructor
public class ItemService implements IItemService {
    
    private final ItemRepository        itemRepository;
    private final BrandRepository       brandRepository;
    private final UserServiceClient    usersServiceClient;
    private final VoucherItemRepository voucherItemRepository;

    @Override
    public List<ItemDto> fetchAllItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream().map(ItemMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ItemDto fetchItemById(String id) {
        Item item = itemRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Item", "id", id)
        );
        return ItemMapper.toDto(item);
    }

    @Override
    public List<ItemDto> fetchItemsByIds(List<String> ids) {
        List<Item> items = itemRepository.findByIds(ids);
        return items.stream().map(ItemMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> fetchItemsByBrand(String brandId) {
        List<Item> items = itemRepository.findByBrand(brandId);
        return items.stream().map(ItemMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> fetchItemsByBrands(List<String> brandIds) {
        List<Item> items = itemRepository.findByBrands(brandIds);
        return items.stream().map(ItemMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ReturnItemDto> fetchItemsByVoucher(String voucherId) {
        List<VoucherItem> voucherItems = voucherItemRepository.findByVoucher(voucherId);
        List<ReturnItemDto> returnItemDtos = new ArrayList<>();

        for (VoucherItem voucherItem : voucherItems) {
            VoucherItemDto voucherItemDto = VoucherItemMapper.toDto(voucherItem);
            
            returnItemDtos.add(new ReturnItemDto(voucherItemDto.getItem().getId(),
                                                voucherItemDto.getItem().getBrand_id(),
                                                voucherItemDto.getItem().getName(),
                                                voucherItemDto.getItem().getIcon(),
                                                voucherItemDto.getItem().getDescription(),
                                                voucherItemDto.getNumberOfItem()));
        }

        return returnItemDtos;
    }

    @Override
    public ItemDto createItem(ItemDto itemDto) {

        BrandDto brandDto = usersServiceClient.getBrand(itemDto.getBrand_id());
                     
        Item item = ItemMapper.toEntity(itemDto);
        item.setBrand_id(brandDto.getId());
        itemRepository.save(item);
        return ItemMapper.toDto(item);
    }

    @Override
    public boolean updateItem(ItemDto itemDto) {
        try {
            Item item = itemRepository.findById(itemDto.getId()).orElseThrow(
                    () -> new NotFoundException("Item", "id", itemDto.getId())
            );

            Item updatedItem = ItemMapper.toEntity(itemDto);
            updatedItem.setId(item.getId());
            itemRepository.save(updatedItem);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean deleteItem(String id) {
        try {
            Item item = itemRepository.findById(id).orElseThrow(
                    () -> new NotFoundException("Item", "id", id)
            );

            itemRepository.delete(item);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}