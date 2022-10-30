package org.filatov.crmapp.repository;

import org.filatov.crmapp.domain.Client;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface ClientRepository extends ReactiveSortingRepository<Client, Long> {
}
