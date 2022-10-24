package org.filatov.crmapp.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.filatov.crmapp.domain.Client;
import org.filatov.crmapp.service.DBService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component("ReactiveWebSocketHandler")
@RequiredArgsConstructor
public class ReactiveWebSocketHandler implements WebSocketHandler {

    private static final ObjectMapper json = new ObjectMapper();

    private final DBService<Client> service;

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        Flux<Client> all = service.findAll();
        return session.send(
                all.map(
                        client -> {
                            String s;
                            try {
                                s = json.writeValueAsString(client);
                            } catch (JsonProcessingException e) {
                                throw new RuntimeException(e);
                            }
                            return s;
                        }
                ).map(session::textMessage)
        )
                .and(session
                        .receive()
                        .map(WebSocketMessage::getPayloadAsText));
    }
}
