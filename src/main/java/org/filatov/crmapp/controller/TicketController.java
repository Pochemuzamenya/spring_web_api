package org.filatov.crmapp.controller;

import lombok.RequiredArgsConstructor;
import org.filatov.crmapp.domain.Ticket;
import org.filatov.crmapp.service.DBService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RequestMapping("/api/ticket")
@RestController
@CrossOrigin
public class TicketController {

    private final DBService<Ticket> service;

    @GetMapping("/all")
    public Flux<Ticket> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Mono<Ticket> create(@RequestBody Ticket ticket) {
        return service.save(ticket);
    }

    @PutMapping("/update")
    public Mono<Ticket> update(@RequestBody Ticket ticket) {
        return service.update(ticket);
    }

    @DeleteMapping("/{id}")
    public Mono<Ticket> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
