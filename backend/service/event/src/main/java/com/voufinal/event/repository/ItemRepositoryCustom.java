package com.voufinal.event.repository;

import java.util.List;

import com.voufinal.event.entity.Item;

public interface ItemRepositoryCustom {
    List<Item> findByBrand(String brandId);
    List<Item> findByBrands(List<String> brandIds);
    List<Item> findByIds(List<String> ids);
}
