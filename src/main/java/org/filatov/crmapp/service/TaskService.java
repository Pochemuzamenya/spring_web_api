package org.filatov.crmapp.service;

import lombok.RequiredArgsConstructor;
import org.filatov.crmapp.domain.Task;
import org.filatov.crmapp.repository.TaskRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class TaskService implements DBService<Task> {

    private final TaskRepository repo;

    @Override
    public Mono<Task> save(Task task) {
        return repo.save(task);
    }

    @Override
    public Flux<Task> findAll() {
        return repo.findAll();
    }

    @Override
    public Mono<Task> update(Task task) {
        return repo.save(task);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return repo.deleteById(id);
    }
}
