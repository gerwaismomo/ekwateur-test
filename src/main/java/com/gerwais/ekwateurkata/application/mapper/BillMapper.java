package com.gerwais.ekwateurkata.application.mapper;

import com.gerwais.ekwateurkata.application.dto.BillBodyDto;
import com.gerwais.ekwateurkata.application.dto.BillDto;
import com.gerwais.ekwateurkata.application.dto.ConsumptionDto;
import com.gerwais.ekwateurkata.domain.model.EnergyType;
import com.gerwais.ekwateurkata.domain.model.billing.Bill;
import com.gerwais.ekwateurkata.domain.model.billing.Consumption;
import com.gerwais.ekwateurkata.domain.usecase.billing.BillProcessCommand;

import java.util.List;
import java.util.stream.Collectors;

public interface BillMapper {
    static BillProcessCommand toCommand(BillBodyDto billBodyDto) {

        return new BillProcessCommand.Builder()
                .withReferenceClient(billBodyDto.getReferenceClient())
                .withYear(billBodyDto.getYear())
                .withMonth(billBodyDto.getMonth())
                .withConsumptions(toCommand(billBodyDto.getConsumptions()))
                .build();

    }

    private static List<Consumption> toCommand(List<ConsumptionDto> consumptions) {
        return consumptions.stream().map(BillMapper::toCommand).collect(Collectors.toList());
    }

    public static Consumption toCommand(ConsumptionDto dto) {
        return new Consumption.Builder()
                .withEnergyType(EnergyType.map(dto.getEnergyType()))
                .withConsumption(dto.getConsumption())
                .build();
    }

    static BillDto toDto(Bill bill) {

        return BillDto.builder()
                .id(bill.getId())
                .client(bill.getClient())
                .year(bill.getYear())
                .month(bill.getMonth())
                .total(bill.getTotal())
                .build();

    }
}
