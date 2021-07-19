package com.example.test.service.jwt;


import com.example.test.security.CustomUserDetails;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
        private static final String SECRET_KEY = "testSocketJwt";
        private static final long EXPIRE_TIME = 86400000000L;
        private static final Logger LOGGER = LoggerFactory.getLogger(JwtService.class.getName());

        public String generateTokenLogin(Authentication authentication){
            CustomUserDetails userPrinciple = (CustomUserDetails) authentication.getPrincipal();

            return Jwts.builder()
                    .setSubject(userPrinciple.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime() + EXPIRE_TIME*100))
                    .signWith(SignatureAlgorithm.HS512 , SECRET_KEY)
                    .compact();
        }

        public boolean validateJwtToken(String authToken){
            try {
                Jwts.parser()
                        .setSigningKey(SECRET_KEY)
                        .parseClaimsJws(authToken);
                return true;
            }catch (SignatureException e) {
                LOGGER.error("Invalid JWT signature -> Message: {} ", e);
            } catch (MalformedJwtException e) {
                LOGGER.error("Invalid JWT token -> Message: {}", e);
            } catch (ExpiredJwtException e) {
                LOGGER.error("Expired JWT token -> Message: {}", e);
            } catch (UnsupportedJwtException e) {
                LOGGER.error("Unsupported JWT token -> Message: {}", e);
            } catch (IllegalArgumentException e) {
                LOGGER.error("JWT claims string is empty -> Message: {}", e);
            }
            return false;
        }

        public String getUserNameFromJwtToken(String token){
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody().getSubject();
        }
    }


