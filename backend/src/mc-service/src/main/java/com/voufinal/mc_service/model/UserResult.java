package com.voufinal.mc_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResult {
    private String userId;
    private String score;


    public String getUserId() {
        return userId;
    }

    public String getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "UserResult{" +
                "userId='" + userId + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
