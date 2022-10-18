package org.filatov.crmapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="client_table")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String taxIdentificationNumber; //ИНН

    private String headInformation; //Сведения о руководителе

    private String bankDetails;

    private String clientAddress;

    private String comment;

    private String contactName;

    private String email;

    private String phoneNumber;

    private String contactType;

    private String region;

    private String contactPersonPosition;

    private String takeFrom;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="manager_id")
    private Manager manager;

}
