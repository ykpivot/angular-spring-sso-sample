package com.sso.backend.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RestController
public class TodoController {

    @Value("${RESOURCE_URL}")
    private String resourceServerUrl;

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/api/todos")
    public ResponseEntity<?> todo() {
        if (resourceServerUrl.equals("https://resource-server-sample.<your-domain>.com")) {
            return new ResponseEntity<>("Not configured", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            return new ResponseEntity<>(todoService.getAll(), HttpStatus.OK);
        } catch (WebClientResponseException error) {
            System.out.println("ERROR: " + error.getStatusText());
        }
        return new ResponseEntity<>("Something happened", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
