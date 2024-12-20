package com.voufinal.event.repository;

import com.voufinal.event.entity.EventVoucher;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

import com.voufinal.event.common.EventIntermediateTableStatus;

public class EventVoucherRepositoryImpl implements EventVoucherRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public EventVoucher findByEventAndVoucher(String eventId, String voucherId) {
        TypedQuery<EventVoucher> query = entityManager.createQuery(
                "SELECT ev FROM EventVoucher ev WHERE ev.event.id = :eventId AND ev.voucher.id = :voucherId",
                EventVoucher.class
        );
        query.setParameter("eventId", eventId);
        query.setParameter("voucherId", voucherId);
        query.setMaxResults(1); // Limit results to 1
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<EventVoucher> findByEvent(String eventId) {
        TypedQuery<EventVoucher> query = entityManager.createQuery(
                "SELECT ev FROM EventVoucher ev WHERE ev.event.id = :eventId AND ev.activeStatus = :activeStatus",
                EventVoucher.class
        );
        query.setParameter("eventId", eventId);
        query.setParameter("activeStatus", EventIntermediateTableStatus.ACTIVE);
        return query.getResultList();
    }
}
