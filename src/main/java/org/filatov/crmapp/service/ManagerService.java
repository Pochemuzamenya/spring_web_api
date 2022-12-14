package org.filatov.crmapp.service;

import lombok.RequiredArgsConstructor;
import org.filatov.crmapp.domain.Manager;
import org.filatov.crmapp.repository.ManagerRepository;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerService implements DBService<Manager>, ReactiveUserDetailsService {

    private final ManagerRepository repo;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return Mono.fromCallable(
                        () -> repo.findByUsername(username)
                ).subscribeOn(Schedulers.boundedElastic())
                .cast(UserDetails.class);
    }

    @Override
    public Mono<Manager> save(Manager manager) {
        return Mono.fromCallable(
                () -> repo.save(manager)
                )
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<Manager> findAll() {
        return Flux.defer(() -> Flux.fromIterable(repo.findAll())).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Manager> update(Manager manager) {
        return Mono.fromCallable(
                () -> repo.save(manager)
        ).subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Manager> findById(Long id) {
        Optional<Manager> manager = repo.findById(id);
        return manager.map(value -> Mono.fromCallable(() -> value)).orElseGet(Mono::empty);
    }

    @Override
    public Mono<Manager> delete(Long id) {
        repo.deleteById(id);
        return Mono.empty();
    }

}
