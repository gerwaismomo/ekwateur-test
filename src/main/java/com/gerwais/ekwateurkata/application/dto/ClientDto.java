package com.gerwais.ekwateurkata.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDto {


    private String referenceClient;
    private String civility;
    private String name;
    private String firstname;
    private String siretNo;
    private String companyName;
    private Long annualSales;

}
