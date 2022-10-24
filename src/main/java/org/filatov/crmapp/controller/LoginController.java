package org.filatov.crmapp.controller;

import lombok.RequiredArgsConstructor;
import org.filatov.crmapp.configuration.JwtUtil;
import org.filatov.crmapp.domain.Manager;
import org.filatov.crmapp.service.ManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final ManagerService service;
    private final JwtUtil jwtUtil;
    private final static ResponseEntity<Object> UNAUTHORIZED = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    @PostMapping("/login")
    public Mono<ResponseEntity> login(ServerWebExchange serverWebExchange) {
        return serverWebExchange.getFormData().flatMap(
                credentials -> service.findByUsername(
                                credentials.getFirst("username")
                        ).cast(Manager.class)
                        .map(
                                userDetails -> Objects.equals(
                                        credentials.getFirst("password"),
                                        userDetails.getPassword()
                                )
                                        ? ResponseEntity.ok(jwtUtil.generateToken(userDetails))
                                        : UNAUTHORIZED
                        )
                        .defaultIfEmpty(UNAUTHORIZED)
        );
    }
}
