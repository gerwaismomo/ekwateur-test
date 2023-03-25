package com.gerwais.ekwateurkata.application.dto;

import com.gerwais.ekwateurkata.domain.model.client.Client;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BillDto {

    private Long id;
    private Client client;
    private int year;
    private int month;
    private BigDecimal total;
}
