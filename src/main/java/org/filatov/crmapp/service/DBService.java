package org.filatov.crmapp.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DBService<T> {

    Mono<T> save(T t);

    Flux<T> findAll();

    Mono<T> update(T t);

    Mono<T> delete(Long id);
}
