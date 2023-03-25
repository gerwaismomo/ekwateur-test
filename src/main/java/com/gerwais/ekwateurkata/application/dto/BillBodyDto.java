package com.gerwais.ekwateurkata.application.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Builder
public class BillBodyDto {

    @NotBlank
    @Pattern(regexp = "EKW\\d{8}")
    private String referenceClient;

    @NotNull
    private Integer year;

    @NotNull
    private Integer month;
    List<ConsumptionDto> consumptions;
}
