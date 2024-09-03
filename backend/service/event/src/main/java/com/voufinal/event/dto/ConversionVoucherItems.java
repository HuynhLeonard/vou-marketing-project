package com.voufinal.event.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import com.voufinal.event.common.ItemId_Quantity;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConversionVoucherItems {
    private String voucherId;
    private List<ItemId_Quantity> itemIds_quantities;
}