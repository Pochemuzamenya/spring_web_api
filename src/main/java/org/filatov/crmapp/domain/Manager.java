package org.filatov.crmapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "manager_table")
public class Manager {

    @Id
    private Long id;

    private String firstname;

    private String lastname;

    private String avatarURL;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

}
