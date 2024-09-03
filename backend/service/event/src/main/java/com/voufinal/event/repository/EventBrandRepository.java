package com.voufinal.event.repository;

import com.voufinal.event.entity.EventBrand;
import com.voufinal.event.model.EventBrandId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventBrandRepository extends JpaRepository<EventBrand, EventBrandId>, EventBrandRepositoryCustom {
    EventBrand findByEventAndBrand(String eventId, String brandId);
    List<EventBrand> findByBrand(String brandId);
    List<EventBrand> findByEvent(String eventId);
    List<EventBrand> findByBrands(List<String> brandIds);
}