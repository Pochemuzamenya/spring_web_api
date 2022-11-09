package org.filatov.crmapp.repository;

import org.filatov.crmapp.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

    Manager findByUsername(String name);
}
