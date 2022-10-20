package org.filatov.crmapp.controller;

import lombok.RequiredArgsConstructor;
import org.filatov.crmapp.domain.Client;
import org.filatov.crmapp.domain.Task;
import org.filatov.crmapp.service.DBService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RequestMapping("/api/task")
@RestController
@CrossOrigin
public class TaskController {

    private final DBService<Task> service;

    @GetMapping
    public Flux<Task> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Mono<Task> create(@RequestBody Task task) {
        return service.save(task);
    }

    @PutMapping
    public Mono<Task> update(@RequestBody Task task) {
        return service.update(task);
    }

    @DeleteMapping("/{id}")
    public Mono<Task> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
