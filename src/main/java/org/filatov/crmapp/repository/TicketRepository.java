package org.filatov.crmapp.repository;

import org.filatov.crmapp.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
