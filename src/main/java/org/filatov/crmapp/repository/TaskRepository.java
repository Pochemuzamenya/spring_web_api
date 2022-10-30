package org.filatov.crmapp.repository;

import org.filatov.crmapp.domain.Task;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface TaskRepository extends ReactiveSortingRepository<Task, Long> {
}
