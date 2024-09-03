package com.voufinal.user_service.config;

import com.nimbusds.jwt.SignedJWT;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;

@Component
public class CustomJWTDecode implements JwtDecoder {

    private static final Logger log = LoggerFactory.getLogger(CustomJWTDecode.class);

    @Override
    public Jwt decode(String token) throws JwtException {
        try{
            log.info("Decoding JWT token: {}", token);
            SignedJWT signedJWT = SignedJWT.parse(token);

            log.info("JWT Claims: {}", signedJWT.getJWTClaimsSet().getClaims());

            return new Jwt(token,
                    signedJWT.getJWTClaimsSet().getIssueTime().toInstant(),
                    signedJWT.getJWTClaimsSet().getExpirationTime().toInstant(),
                    signedJWT.getHeader().toJSONObject(),
                    signedJWT.getJWTClaimsSet().getClaims()
            );

        } catch (ParseException e) {
            log.error("Invalid token: {}", token, e);
            throw new RuntimeException(e);
        }
    }
}
