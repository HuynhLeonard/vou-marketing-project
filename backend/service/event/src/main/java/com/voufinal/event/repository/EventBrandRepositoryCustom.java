package com.voufinal.event.repository;

import com.voufinal.event.entity.EventBrand;

import java.util.List;

public interface EventBrandRepositoryCustom {
    EventBrand findByEventAndBrand(String eventId, String brandId);
    List<EventBrand> findByBrand(String brandId);
    List<EventBrand> findByEvent(String eventId);
    List<EventBrand> findByBrands(List<String> brandIds);
}