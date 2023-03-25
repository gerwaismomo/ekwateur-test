package com.gerwais.ekwateurkata.application.dto;

import com.gerwais.ekwateurkata.application.validator.ValueOfEnum;
import com.gerwais.ekwateurkata.domain.model.EnergyType;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class ConsumptionDto {
    @NotNull
    @ValueOfEnum(enumClass = EnergyType.class)
    private String energyType;
    @NotNull
    private Integer consumption;
}
