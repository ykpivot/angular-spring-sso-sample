package com.sso.backend.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ApiController {

    @Value("${ssoServiceUrl:placeholder}")
    String ssoServiceUrl;

    private ObjectMapper objectMapper;

    public ApiController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping("/user-info")
    public ResponseEntity<?> userInfo(Principal principal) throws Exception {
        if (principal == null || principal.getName().isEmpty()) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        System.out.println("USER: " + toPrettyJsonString(principal.getName()));
        return new ResponseEntity<>(principal.getName(), HttpStatus.OK);
    }

    private String toPrettyJsonString(Object object) throws Exception {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }
}
