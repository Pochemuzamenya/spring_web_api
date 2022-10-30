package org.filatov.crmapp.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.filatov.crmapp.domain.view.Views;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("manager_table")
public class Manager {

    @Id
    @JsonView(Views.IdName.class)
    private Long id;

    private String name;

    private String avatarURL;

}
