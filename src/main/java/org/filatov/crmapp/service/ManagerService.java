package org.filatov.crmapp.service;

import lombok.RequiredArgsConstructor;
import org.filatov.crmapp.domain.Manager;
import org.filatov.crmapp.repository.ManagerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ManagerService implements DBService<Manager> {

    private final ManagerRepository repo;

    @Override
    public Mono<Manager> save(Manager manager) {
        return repo.save(manager);
    }

    @Override
    public Flux<Manager> findAll() {
        return repo.findAll();
    }

    @Override
    public Mono<Manager> update(Manager manager) {
        return repo.save(manager);
    }


    @Override
    public Mono<Void> delete(Long id) {
        return repo.deleteById(id);
    }

}
