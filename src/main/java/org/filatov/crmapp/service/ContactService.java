package org.filatov.crmapp.service;

import lombok.RequiredArgsConstructor;
import org.filatov.crmapp.domain.Contact;
import org.filatov.crmapp.repository.ContactRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class ContactService implements DBService<Contact> {

    private final ContactRepository repo;

    @Override
    public Mono<Contact> save(Contact contact) {
        return Mono.fromCallable(
                ()->repo.save(contact)
                )
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<Contact> findAll() {
        return Flux.fromIterable(repo.findAll()).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Contact> update(Contact contact) {
        return Mono.fromCallable(
                () -> repo.save(contact))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Contact> delete(Long id) {
        repo.deleteById(id);
        return Mono.empty();
    }
}
