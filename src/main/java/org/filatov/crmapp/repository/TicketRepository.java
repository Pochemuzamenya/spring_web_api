package org.filatov.crmapp.repository;

import org.filatov.crmapp.domain.Client;
import org.filatov.crmapp.domain.Ticket;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;

public interface TicketRepository extends ReactiveSortingRepository<Ticket, Long> {
    Flux<Ticket> findAllByClient(Client client);

    Flux<Ticket> findAllByClientId(Long id);
}
