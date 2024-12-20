package com.voufinal.event.repository;

import java.util.Map;
import java.util.stream.Collectors;

import com.voufinal.event.common.EventIntermediateTableStatus;
import com.voufinal.event.entity.VoucherItem;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import com.voufinal.event.common.EventIntermediateTableStatus;

import java.util.List;

public class VoucherItemRepositoryImpl implements VoucherItemRepositoryCustom {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public VoucherItem findByVoucherAndItem(String voucherId, String itemId) {
        TypedQuery<VoucherItem> query = entityManager.createQuery(
                "SELECT vi FROM VoucherItem vi WHERE vi.voucher.id = :voucherId AND vi.item.id = :itemId",
                VoucherItem.class
        );
        query.setParameter("voucherId", voucherId);
        query.setParameter("itemId", itemId);
        query.setMaxResults(1); // Limit results to 1
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Map<String, Integer> getItemsQuantitiesByVoucher(String voucherId) {
        TypedQuery<VoucherItem> query = entityManager.createQuery(
                "SELECT vi FROM VoucherItem vi WHERE vi.voucher.id = :voucherId AND vi.activeStatus = :activeStatus",
                VoucherItem.class
        );
        query.setParameter("voucherId", voucherId);
        query.setParameter("activeStatus", EventIntermediateTableStatus.ACTIVE);
        return query.getResultList().stream().collect(
                Collectors.toMap(
                        vi -> vi.getItem().getId(),
                        VoucherItem::getNumberOfItem
                )
        );
    }

    @Override
    public List<VoucherItem> findByVoucher(String voucherId) {
        TypedQuery<VoucherItem> query = entityManager.createQuery(
                "SELECT vi FROM VoucherItem vi WHERE vi.voucher.id = :voucherId AND vi.activeStatus = :activeStatus",
                VoucherItem.class
        );
        query.setParameter("voucherId", voucherId);
        query.setParameter("activeStatus", EventIntermediateTableStatus.ACTIVE);
        return query.getResultList();
    }
}
