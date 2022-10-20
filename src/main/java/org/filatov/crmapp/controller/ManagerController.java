package org.filatov.crmapp.controller;

import lombok.RequiredArgsConstructor;
import org.filatov.crmapp.domain.Manager;
import org.filatov.crmapp.service.DBService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor
@CrossOrigin
public class ManagerController {

    private final DBService<Manager> service;

    @GetMapping
    public Flux<Manager> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Mono<Manager> create(@RequestBody Manager manager) {
        return service.save(manager);
    }

    @PutMapping
    public Mono<Manager> update(@RequestBody Manager manager) {
        return service.update(manager);
    }

    @DeleteMapping("/{id}")
    public Mono<Manager> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
