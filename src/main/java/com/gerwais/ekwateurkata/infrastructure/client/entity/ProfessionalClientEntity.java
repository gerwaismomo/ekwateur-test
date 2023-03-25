package com.gerwais.ekwateurkata.infrastructure.client.entity;

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
@Entity(name = "ProClient")
@DiscriminatorValue("Pro")
public class ProfessionalClientEntity extends ClientEntity {


    private String siretNo;
    private String companyName;
    private Long annualSales;
}
