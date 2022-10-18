package org.filatov.crmapp.repository;

import org.filatov.crmapp.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
