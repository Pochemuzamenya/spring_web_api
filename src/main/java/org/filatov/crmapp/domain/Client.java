package org.filatov.crmapp.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Table("client_table")
public class Client {

    @Id
    private Long id;

    private String title;

    private String taxIdentificationNumber; //ИНН

    private String clientAddress;

    private String email;

    private String phoneNumber;

    private String comment;

    private String contactName;

    private String region;

    @JsonIgnore
    private Manager manager;

}
