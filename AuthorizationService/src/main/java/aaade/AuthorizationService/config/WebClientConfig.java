package aaade.AuthorizationService.config;

import aaade.AuthorizationService.interceptor.LoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebClientConfig class provides configuration for the WebClient and PasswordEncoder beans.
 * It also configures the LoggingInterceptor.
 */
@Configuration
public class WebClientConfig implements WebMvcConfigurer {
    @Autowired
    private LoggingInterceptor loggingInterceptor;

    /**
     * Creates a WebClient bean.
     *
     * @return a new instance of WebClient
     */
    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }

    /**
     * Creates a PasswordEncoder bean.
     *
     * @return a new instance of BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Adds the LoggingInterceptor to the InterceptorRegistry.
     *
     * @param registry the InterceptorRegistry to which the LoggingInterceptor is added
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor);
    }
}