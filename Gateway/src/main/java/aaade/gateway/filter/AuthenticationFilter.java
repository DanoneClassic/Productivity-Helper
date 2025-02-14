package aaade.gateway.filter;

import aaade.gateway.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * AuthenticationFilter class provides a filter for authenticating requests.
 */
@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final JwtUtil jwtUtil;

    /**
     * Constructs an AuthenticationFilter with the given JwtUtil.
     *
     * @param jwtUtil the JwtUtil to use for token validation
     */
    @Autowired
    public AuthenticationFilter(JwtUtil jwtUtil) {
        super(Config.class);
        this.jwtUtil = jwtUtil;
    }

    public static class Config {
    }

    /**
     * Applies the filter to the request.
     *
     * @param config the configuration for the filter
     * @return the GatewayFilter
     */
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String token = getTokenFromRequest(exchange);
            if (token == null || !isTokenValid(token)) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                log.error("Token is not valid");
                return exchange.getResponse().setComplete();
            }
            log.info("Token is valid");
            return chain.filter(exchange);
        };
    }

    /**
     * Retrieves the token from the request.
     *
     * @param exchange the ServerWebExchange
     * @return the token from the request
     */
    private String getTokenFromRequest(ServerWebExchange exchange) {
        String bearerToken = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    /**
     * Validates the token.
     *
     * @param token the token to validate
     * @return true if the token is valid, false otherwise
     */
    private boolean isTokenValid(String token) {
        try {
            log.info("Validating token: {}", token);
            Claims claims = jwtUtil.validateToken(token);
            return claims != null;
        } catch (Exception e) {
            log.error("Token validation failed", e);
            return false;
        }
    }
}