package org.filatov.crmapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.REFRESH;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "managers")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "manager", cascade = {DETACH,MERGE,PERSIST,REFRESH}, fetch = FetchType.EAGER)
    private Set<Client> clients = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "manager", cascade = {DETACH,MERGE,PERSIST,REFRESH}, fetch = FetchType.EAGER)
    private Set<Ticket> tickets = new HashSet<>();
}
