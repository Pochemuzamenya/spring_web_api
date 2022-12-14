package org.filatov.crmapp.configuration;

import lombok.RequiredArgsConstructor;
import org.filatov.crmapp.domain.Manager;
import org.filatov.crmapp.domain.Ticket;
import org.filatov.crmapp.domain.view.Views;
import org.filatov.crmapp.service.ManagerService;
import org.filatov.crmapp.service.TicketService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2CodecSupport;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
@RequiredArgsConstructor
public class RouterConfig {

    private final TicketService ticketService;
    private final ManagerService managerService;

    @Bean
    public RouterFunction<ServerResponse> getTicketByClientRoute() {
        return route(GET("/ticket/{id}"),
                request -> ok().body(
                        ticketService.findByClientId(Long.valueOf(request.pathVariable("id"))), Ticket.class)
        ).andRoute(GET("/manager/{id}"),
                request -> ok()
                        .hint(Jackson2CodecSupport.JSON_VIEW_HINT, Views.IdName.class)
                        .body(
                managerService.findById(Long.valueOf(request.pathVariable("id")))
                , Manager.class));
    }
}
