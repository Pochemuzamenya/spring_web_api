package org.filatov.crmapp.repository;

import org.filatov.crmapp.domain.Client;
import org.filatov.crmapp.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByClient(Client client);

    List<Ticket> findAllByClientId(Long id);
}
