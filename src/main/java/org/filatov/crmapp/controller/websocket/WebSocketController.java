package org.filatov.crmapp.controller.websocket;

import lombok.RequiredArgsConstructor;
import org.filatov.crmapp.domain.Client;
import org.filatov.crmapp.service.DBService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
@RequiredArgsConstructor
public class WebSocketController {

    private final DBService<Client> service;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Flux<Client> greeting() {
        return service.findAll();
    }
}
