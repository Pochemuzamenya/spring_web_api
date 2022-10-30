package org.filatov.crmapp.repository;

import org.filatov.crmapp.domain.Manager;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface ManagerRepository extends ReactiveSortingRepository<Manager, Long> {
}
