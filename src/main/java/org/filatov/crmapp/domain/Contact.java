package org.filatov.crmapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    private String contactType;

    private String comment;

    private String region;

    @ManyToOne(fetch = FetchType.EAGER)
    private Manager manager;

    @Builder.Default
    @ManyToMany(mappedBy = "contactPersons", fetch = FetchType.EAGER)
    private Set<Client> clients = new HashSet<>(); //Компании представителя

    @Builder.Default
    @OneToMany(mappedBy = "contact", fetch = FetchType.EAGER)
    private Set<Ticket> tickets = new HashSet<>();

}
