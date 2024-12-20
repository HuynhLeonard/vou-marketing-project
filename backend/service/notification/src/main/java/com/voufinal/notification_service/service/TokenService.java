package com.voufinal.notification_service.service;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.voufinal.notification_service.entity.UserToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class TokenService {
    // private UserTokenRepository userTokenRepository;
    private final Firestore firestore;
    // private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void saveOrUpdateToken(String userId, String fcmToken) {
        CollectionReference tokensRef = firestore.collection("users_tokens");

        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("user_id", userId);
        tokenData.put("token", fcmToken);
        tokenData.put("updated_at", FieldValue.serverTimestamp());

        tokensRef.document(userId).set(tokenData);
    }

    public String getTokenByUserId(String userId) throws Exception {
        DocumentReference userTokenRef = firestore.collection("users_tokens").document(userId);

        UserToken userToken = userTokenRef.get().get().toObject(UserToken.class);

        if (userToken == null) {
            throw new Exception("Token not found");
        }

        return userToken.getToken();
    }
}
