package com.voufinal.event.common;

import com.voufinal.event.dto.ItemDto;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemQuantity {
    private ItemDto item;
    private int quantity;
}
