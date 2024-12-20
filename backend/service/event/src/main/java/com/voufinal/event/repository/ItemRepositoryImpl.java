package com.voufinal.event.repository;

import java.util.List;

import com.voufinal.event.entity.Item;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

public class ItemRepositoryImpl implements ItemRepositoryCustom {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Item> findByBrand(String brandId) {
        TypedQuery<Item> query = entityManager.createQuery(
                "SELECT i FROM Item i WHERE i.brand_id = :brandId",
                Item.class
        );
        query.setParameter("brandId", brandId);
        return query.getResultList();
    }

    @Override
    public List<Item> findByBrands(List<String> brandIds) {
        TypedQuery<Item> query = entityManager.createQuery(
                "SELECT i FROM Item i WHERE i.brand_id IN :brandIds",
                Item.class
        );
        query.setParameter("brandIds", brandIds);
        return query.getResultList();
    }

    @Override
    public List<Item> findByIds(List<String> ids) {
        TypedQuery<Item> query = entityManager.createQuery(
                "SELECT i FROM Item i WHERE i.id IN :ids",
                Item.class
        );
        query.setParameter("ids", ids);
        return query.getResultList();
    }
}
