package com.voufinal.identity.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

// use for login
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest {
    String username;
    String password;
}
