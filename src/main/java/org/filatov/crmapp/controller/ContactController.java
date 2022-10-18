package org.filatov.crmapp.controller;

import lombok.RequiredArgsConstructor;
import org.filatov.crmapp.domain.Contact;
import org.filatov.crmapp.service.DBService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RequestMapping("/api/contact")
@RestController
@CrossOrigin
public class ContactController {

    private final DBService<Contact> service;

    @GetMapping("/all")
    public Flux<Contact> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Mono<Contact> create(@RequestBody Contact contact) {
        return service.save(contact);
    }

    @PutMapping("/update")
    public Mono<Contact> update(@RequestBody Contact contact) {
        return service.update(contact);
    }

    @DeleteMapping("/{id}")
    public Mono<Contact> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
