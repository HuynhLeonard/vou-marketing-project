package com.voufinal.event.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.voufinal.event.common.ItemId_Quantity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddItemRequestDto {
    private String eventId;
    private List<ItemId_Quantity> itemId_Quantities;
}