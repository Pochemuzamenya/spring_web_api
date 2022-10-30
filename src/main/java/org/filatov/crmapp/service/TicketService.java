package org.filatov.crmapp.service;

import lombok.RequiredArgsConstructor;
import org.filatov.crmapp.domain.Client;
import org.filatov.crmapp.domain.Ticket;
import org.filatov.crmapp.repository.TicketRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService implements DBService<Ticket> {

    private final TicketRepository repo;

    @Override
    public Mono<Ticket> save(Ticket ticket) {
        return repo.save(ticket);
    }

    @Override
    public Flux<Ticket> findAll() {
        return repo.findAll();
    }

    @Override
    public Mono<Ticket> update(Ticket ticket) {
        return repo.save(ticket);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return repo.deleteById(id);
    }

    public Flux<Ticket> findByClient(Client client) {
        return repo.findAllByClient(client);
    }

    public Flux<Ticket> findByClientId(Long id) {
        return repo.findAllByClientId(id);
    }
}
