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
        return Mono.fromCallable(
                        () -> repo.save(ticket)
                )
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<Ticket> findAll() {
        return Flux.fromIterable(repo.findAll())
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Ticket> update(Ticket ticket) {
        return Mono.fromCallable(
                        () -> repo.save(ticket)
                )
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Ticket> delete(Long id) {
        repo.deleteById(id);
        return Mono.empty();
    }

    public Flux<Ticket> findByClient(Client client) {

        List<Ticket> allByClient = repo.findAllByClient(client);
        System.out.println(allByClient);
        return Flux.fromIterable(allByClient);
    }

    public Flux<Ticket> findByClientId(Long id) {
        List<Ticket> all = repo.findAllByClientId(id);
        System.out.println(all);
        return Flux.fromIterable(all);
    }
}
