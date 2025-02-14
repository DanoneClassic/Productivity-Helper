package aaade.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }

    @Bean
    public WebClient taskServiceClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8083")
                .build();
    }

    @Bean
    public WebClient workSpaceServiceClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8083")
                .build();
    }
}
