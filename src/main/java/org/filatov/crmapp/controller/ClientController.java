package org.filatov.crmapp.controller;

import lombok.RequiredArgsConstructor;
import org.filatov.crmapp.domain.Client;
import org.filatov.crmapp.service.DBService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RequestMapping("/api/client")
@RestController
@CrossOrigin
public class ClientController {

    private final DBService<Client> service;

    @GetMapping("/all")
    public Flux<Client> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Mono<Client> create(@RequestBody Client client) {
        return service.save(client);
    }

    @PutMapping("/update")
    public Mono<Client> update(@RequestBody Client client) {
        return service.update(client);
    }

    @DeleteMapping("/{id}")
    public Mono<Client> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
