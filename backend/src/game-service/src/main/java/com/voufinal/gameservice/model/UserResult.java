package com.voufinal.gameservice.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
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
        return "{" +
                "userId='" + userId + '\'' +
                ", score=" + score +
                '}';
    }
}