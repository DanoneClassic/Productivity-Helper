package aaade.gateway.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

/**
 * JwtUtil class provides methods for validating JWT tokens.
 */
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    /**
     * Validates a JWT token.
     *
     * @param token the JWT token to validate
     * @return the claims contained in the token
     */
    public Claims validateToken(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Generates a signing key from the secret key.
     *
     * @return the signing key
     */
    private Key getSignKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}