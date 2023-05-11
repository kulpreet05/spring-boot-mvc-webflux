package com.ks.springbootmvcwebflux.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class APIService {
    private final WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:6102/api/v1/job")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
            .build();
    public String monitorJob(long id) {
        return this.getJobStatus(id).repeat().takeUntil(r -> r.equalsIgnoreCase("C")).take(10000).blockLast();
    }

    private Mono<String> getJobStatus(long id) {
        log.info("getJobStatus Begin.");
        return this.webClient.get().uri("/status/{id}", id).retrieve().bodyToMono(String.class);
    }
}
