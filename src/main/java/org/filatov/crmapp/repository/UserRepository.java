package org.filatov.crmapp.repository;

import org.filatov.crmapp.domain.User;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveSortingRepository<User, Long> {
    Mono<User> findByUsername(String name);

}
