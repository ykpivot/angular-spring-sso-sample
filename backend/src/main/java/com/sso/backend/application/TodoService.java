package com.sso.backend.application;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class TodoService {
    private WebClient webClient;

    public TodoService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<Todo> getAll() {
        return this.webClient
                .get()
                .uri("/todos")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Todo>>() {})
                .block();
    }
}
