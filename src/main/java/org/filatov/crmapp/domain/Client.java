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

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String taxIdentificationNumber; //ИНН

    private String headInformation; //Сведения о руководителе

    private String bankDetails;

    private String deliveryAddress;

    private String comment;

    @Builder.Default
    @ManyToMany(cascade = {
            DETACH,MERGE,PERSIST,REFRESH
    }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "client_contact",
            joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "contact_id")
    )
    private Set<Contact> contactPersons = new HashSet<>(); //Представители компании

    private String contactPersonPosition;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="manager_id")
    private Manager manager;

    public void addContact(Contact contact) {
        this.contactPersons.add(contact);
        contact.getClients().add(this);
    }

    public void removeContact(Contact contact) {
        this.contactPersons.remove(contact);
        contact.getClients().remove(this);
    }
}
