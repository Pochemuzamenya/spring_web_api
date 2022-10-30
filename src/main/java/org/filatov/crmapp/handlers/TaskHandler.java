package org.filatov.crmapp.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.filatov.crmapp.domain.Task;
import org.filatov.crmapp.service.TaskService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component("taskHandler")
@RequiredArgsConstructor
public class TaskHandler implements WebSocketHandler {

    private final TaskService service;
    private final ObjectMapper mapper;

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        Flux<WebSocketMessage> map = session
                .receive()
                .map(WebSocketMessage::getPayloadAsText)
                .map(s -> mapper.convertValue(s, Task.class))
                .flatMap(service::save)
                .flatMap(m -> Flux.from(service.findAll()))
                .flatMap(task -> {
                    try {
                        return Mono.just(mapper.writeValueAsString(task));
                    } catch (JsonProcessingException e) {
                        return Mono.error(e);
                    }
                }).map(session::textMessage);
        return session.send(map);
    }
}
