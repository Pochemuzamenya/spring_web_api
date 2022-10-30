package org.filatov.crmapp.service;

import lombok.RequiredArgsConstructor;
import org.filatov.crmapp.domain.Client;
import org.filatov.crmapp.repository.ClientRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class ClientService implements DBService<Client> {

    private final ClientRepository repo;

    @Override
    public Mono<Client> save(Client client) {
        return repo.save(client);
    }

    @Override
    public Flux<Client> findAll() {
        return repo.findAll();
    }

    @Override
    public Mono<Client> update(Client client) {
        return repo.save(client);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return repo.deleteById(id);
    }
}
