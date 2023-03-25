package com.gerwais.ekwateurkata.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProClientDto {

    private Long id;

    @NotBlank
    @Pattern(regexp = "EKW\\d{8}")
    private String referenceClient;

    private String clientType;

    @NotBlank
    @Pattern(regexp = "\\d{14}")
    private String siretNo;

    @NotBlank
    private String companyName;

    private Long annualSales;


}
