package com.voufinal.event.repository;

import com.voufinal.event.entity.Item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, String>, ItemRepositoryCustom {
    List<Item> findByBrand(String brandId);
    List<Item> findByBrands(List<String> brandIds);
    List<Item> findByIds(List<String> ids);
}