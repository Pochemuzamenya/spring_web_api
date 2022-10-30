package org.filatov.crmapp.repository;

import org.filatov.crmapp.domain.Manager;
import org.filatov.crmapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);

    Manager findManagerByUsername(String name);
}
