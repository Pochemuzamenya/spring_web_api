package org.filatov.crmapp.controller;

import lombok.RequiredArgsConstructor;
import org.filatov.crmapp.configuration.JwtUtil;
import org.filatov.crmapp.domain.LoginResponse;
import org.filatov.crmapp.domain.User;
import org.filatov.crmapp.service.UserService;
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

    private final UserService service;
    private final JwtUtil jwtUtil;
    private final static ResponseEntity<Object> UNAUTHORIZED = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    @PostMapping("/login")
    public Mono<ResponseEntity> login(ServerWebExchange serverWebExchange) {


        return serverWebExchange
                .getFormData()
                .flatMap(
                        credentials -> service.findByUsername(
                                        credentials.getFirst("username")
                                ).cast(User.class)
                                .map(
                                        userDetails -> Objects.equals(
                                                credentials.getFirst("password"),
                                                userDetails.getPassword()
                                        )
                                                ? ResponseEntity
                                                .status(HttpStatus.OK)
                                                .body(new LoginResponse(jwtUtil.generateToken(userDetails), userDetails.getId()))
                                                : UNAUTHORIZED
                                )
                                .defaultIfEmpty(UNAUTHORIZED)
                );
    }
}
