package com.voufinal.notification_service.repository;

import com.voufinal.notification_service.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, String>, UserTokenRepositoryCustom{
    UserToken findByUserId(String userId);

    UserToken findByToken(String token);

    String findTokenByUserId(String userId);

    UserToken findByUserIdAndToken(String userId, String token);
}
