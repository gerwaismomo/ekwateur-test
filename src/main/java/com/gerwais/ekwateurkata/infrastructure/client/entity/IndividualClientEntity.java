package com.gerwais.ekwateurkata.infrastructure.client.entity;

import com.gerwais.ekwateurkata.domain.model.client.Civilite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "IndivClient")
@DiscriminatorValue("Indiv")

public class IndividualClientEntity extends ClientEntity {


    private Civilite civility;
    private String name;
    private String firstname;
}
