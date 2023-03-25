package com.gerwais.ekwateurkata.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gerwais.ekwateurkata.application.validator.ValueOfEnum;
import com.gerwais.ekwateurkata.domain.model.client.Civilite;
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
public class IndivClientDto {

    private Long id;

    @NotBlank
    @Pattern(regexp = "EKW\\d{8}")
    private String referenceClient;

    private String clientType;
    @ValueOfEnum(enumClass = Civilite.class)
    private String civility;
    private String name;
    private String firstname;

}
