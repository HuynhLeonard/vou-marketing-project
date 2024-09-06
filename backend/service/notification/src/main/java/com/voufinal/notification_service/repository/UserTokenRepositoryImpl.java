package com.voufinal.notification_service.repository;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.voufinal.notification_service.entity.UserToken;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

@Component
public class UserTokenRepositoryImpl implements UserTokenRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private Firestore firestore;

    @Override
    public UserToken findByToken(String token) {
        TypedQuery<UserToken> query = entityManager.createQuery(
                "SELECT et FROM UserToken et WHERE et.token = :token ORDER BY et.updatedAt DESC",
                UserToken.class
        );
        query.setParameter("token", token);
        query.setMaxResults(1); // Limit results to 1
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public UserToken findByUserId(String userId) {
        TypedQuery<UserToken> query = entityManager.createQuery(
                "SELECT et FROM UserToken et WHERE et.userId = :userId ORDER BY et.updatedAt DESC",
                UserToken.class
        );
        query.setParameter("userId", userId);
        query.setMaxResults(1); // Limit results to 1
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public String findTokenByUserId(String userId) {
        String token = null;

        try {
            // Query query = firestore.collection("users_tokens")
            //         .whereEqualTo("user_id", userId)
            //         .limit(1);

            DocumentReference docRef = firestore.collection("users_tokens").document(userId);

            DocumentSnapshot document = docRef.get().get();
            if (document.exists()) {
                token = document.getString("token");
            }

            // DocumentReference documentReference = query.get().get().getDocuments().get(0).getReference();
            // token = documentReference.get().get().getString("token");

            // List<QueryDocumentSnapshot> documents = query.get().get().getDocuments();
            // if (!documents.isEmpty()) {
            //     DocumentReference documentReference = documents.get(0).getReference();
            //     token = documentReference.get().get().getString("token");
            // }
        } catch (InterruptedException e) {
            // Handle InterruptedException
            Thread.currentThread().interrupt(); // Restore the interrupted status
            e.printStackTrace();
        } catch (ExecutionException e) {
            // Handle ExecutionException
            e.printStackTrace();
        }

        return token;
    }

    @Override
    public UserToken findByUserIdAndToken(String userId, String token) {
        TypedQuery<UserToken> query = entityManager.createQuery(
                "SELECT et FROM UserToken et WHERE et.userId = :userId AND et.token = :token ORDER BY et.updatedAt DESC",
                UserToken.class
        );
        query.setParameter("userId", userId);
        query.setParameter("token", token);
        query.setMaxResults(1); // Limit results to 1
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
