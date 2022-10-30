package org.filatov.crmapp.service;

import lombok.RequiredArgsConstructor;
import org.filatov.crmapp.domain.Manager;
import org.filatov.crmapp.repository.UserRepository;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class UserService implements ReactiveUserDetailsService {

    private final UserRepository repo;
    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return Mono.fromCallable(
                        () -> repo.findByUsername(username)
                ).subscribeOn(Schedulers.boundedElastic())
                .cast(UserDetails.class);
    }

    public Mono<Manager> find(String username) {
        return Mono.fromCallable(() -> repo.findManagerByUsername(username)).subscribeOn(Schedulers.boundedElastic());
    }
}
