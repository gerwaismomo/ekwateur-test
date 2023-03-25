package com.gerwais.ekwateurkata.application.mapper;


import com.gerwais.ekwateurkata.application.dto.ClientDto;
import com.gerwais.ekwateurkata.application.dto.IndivClientDto;
import com.gerwais.ekwateurkata.application.dto.ProClientDto;
import com.gerwais.ekwateurkata.domain.model.client.Civilite;
import com.gerwais.ekwateurkata.domain.model.client.Client;
import com.gerwais.ekwateurkata.domain.model.client.IndividualClient;
import com.gerwais.ekwateurkata.domain.model.client.ProfessionalClient;
import com.gerwais.ekwateurkata.domain.usecase.client.UserCreationCommand;

public interface ClientMapper {


    static UserCreationCommand toCommand(IndivClientDto dto) {
        IndividualClient client = new IndividualClient();
        client.setReferenceClient(dto.getReferenceClient());
        client.setCivility(Civilite.map(dto.getCivility()));
        client.setFirstname(dto.getFirstname());
        client.setName(dto.getName());
        return new UserCreationCommand(client);
    }

    static UserCreationCommand toCommand(ProClientDto dto) {
        ProfessionalClient client = new ProfessionalClient();
        client.setReferenceClient(dto.getReferenceClient());
        Long s = dto.getAnnualSales();
        client.setAnnualSales(s);
        client.setSiretNo(dto.getSiretNo());
        client.setCompanyName(dto.getCompanyName());
        return new UserCreationCommand(client);
    }

    static IndivClientDto toIndivDto(Client client) {
        IndividualClient individualClient = (IndividualClient) client;
        return IndivClientDto.builder()
                .id(client.getId())
                .clientType(client.getClientType().name())
                .referenceClient(individualClient.getReferenceClient())
                .civility(individualClient.getCivility().name())
                .firstname(individualClient.getFirstname())
                .name(individualClient.getName())
                .build();
    }

    static ProClientDto toProDto(Client client) {
        ProfessionalClient individualClient = (ProfessionalClient) client;
        return ProClientDto.builder()
                .id(client.getId())
                .clientType(client.getClientType().name())
                .referenceClient(individualClient.getReferenceClient())
                .companyName(individualClient.getCompanyName())
                .annualSales(individualClient.getAnnualSales())
                .siretNo(individualClient.getSiretNo())
                .build();
    }


}
