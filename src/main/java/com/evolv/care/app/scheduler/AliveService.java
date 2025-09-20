package com.evolv.care.app.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Component to prevent application from spin down in the server
 *
 */
@Slf4j
@Component
@EnableScheduling
public class AliveService {
    private final WebClient webClient;
    @Value("${spring.app.base.url}")
    private String baseUrl;

    @Value("${spring.app.base.auth_token}")
    private String authToken;

    /**
     * Constructor for webclient instance
     * @param webClientBuilder - For webclient
     */
    public AliveService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    /**
     * Scheduler method to make an api call to make the server alive
     */
    @Scheduled(fixedRate = 60000) // Every 60 seconds
    public void keepAlive() {
        String healthCheckUrl = baseUrl + "api/schedule/pinch";
        // log.info(" Health check URL: "+ healthCheckUrl);
        Mono<String> result = webClient.get()
                .uri(healthCheckUrl)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + authToken)
                .retrieve()
                .bodyToMono(String.class);

        result.subscribe(
                response -> {
                    // Handle successful response
                     log.info("Health check response: " + response);
                },
                error -> {
                    // Handle error
                    log.error("Error during health check: " + error.getMessage());
                }
        );
    }
}
