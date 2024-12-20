package com.voufinal.event.mapper;

import com.voufinal.event.dto.ItemDto;
import com.voufinal.event.entity.Item;
import com.voufinal.event.mapper.BrandMapper;

import org.springframework.stereotype.Service;

@Service
public class ItemMapper {

    // Convert Item to ItemDto
    public static ItemDto toDto(Item item) {
        if (item == null) {
            return null;
        }

        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setBrand_id(item.getBrand_id());
        itemDto.setName(item.getName());
        itemDto.setIcon(item.getIcon());
        itemDto.setDescription(item.getDescription());

        return itemDto;
    }

    // Convert ItemDto to Item
    public static Item toEntity(ItemDto dto) {
        if (dto == null) {
            return null;
        }

        Item item = new Item();
        item.setId(dto.getId());
        item.setBrand_id(dto.getBrand_id());
        item.setName(dto.getName());
        item.setIcon(dto.getIcon());
        item.setDescription(dto.getDescription());

        return item;
    }
}
