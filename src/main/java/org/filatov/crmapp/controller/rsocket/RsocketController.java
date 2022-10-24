package org.filatov.crmapp.controller.rsocket;

import lombok.RequiredArgsConstructor;
import org.filatov.crmapp.domain.Client;
import org.filatov.crmapp.service.DBService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
@RequiredArgsConstructor
public class RsocketController {

    private final DBService<Client> service;

    @MessageMapping("tweets.by.author")
    public Flux<Client> getBySomething() {
        return service.findAll();
    }
}
