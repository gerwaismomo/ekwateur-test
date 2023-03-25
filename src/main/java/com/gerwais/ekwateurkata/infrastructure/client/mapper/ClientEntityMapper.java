package com.gerwais.ekwateurkata.infrastructure.client.mapper;

import com.gerwais.ekwateurkata.domain.model.client.Client;
import com.gerwais.ekwateurkata.domain.model.client.ClientType;
import com.gerwais.ekwateurkata.domain.model.client.IndividualClient;
import com.gerwais.ekwateurkata.domain.model.client.ProfessionalClient;
import com.gerwais.ekwateurkata.infrastructure.client.entity.ClientEntity;
import com.gerwais.ekwateurkata.infrastructure.client.entity.IndividualClientEntity;
import com.gerwais.ekwateurkata.infrastructure.client.entity.ProfessionalClientEntity;

public interface ClientEntityMapper {

    static ClientEntity toEntity(Client client) {

        return client.getClientType() == ClientType.INDIV ? toIndivEntity(client) : toProEntity(client);
    }

    private static IndividualClientEntity toIndivEntity(Client client) {
        IndividualClient individualClient = (IndividualClient) client;
        return IndividualClientEntity.builder()
                .clientType(ClientType.INDIV)
                .id(client.getId())
                .referenceClient(individualClient.getReferenceClient())
                .civility(individualClient.getCivility())
                .firstname(individualClient.getFirstname())
                .name(individualClient.getName())
                .build();
    }

    private static ProfessionalClientEntity toProEntity(Client client) {
        ProfessionalClient individualClient = (ProfessionalClient) client;
        return ProfessionalClientEntity.builder()
                .clientType(ClientType.PRO)
                .id(client.getId())
                .referenceClient(individualClient.getReferenceClient())
                .companyName(individualClient.getCompanyName())
                .annualSales(individualClient.getAnnualSales())
                .siretNo(individualClient.getSiretNo())
                .build();
    }

    static Client toDomain(ClientEntity client) {

        return client.getClientType() == ClientType.INDIV ? toIndiv(client) : toPro(client);
    }

    private static IndividualClient toIndiv(ClientEntity client) {
        IndividualClientEntity individualClient = (IndividualClientEntity) client;
        return new IndividualClient.Builder(client.getReferenceClient())
                .withId(client.getId())
                .withCivility(individualClient.getCivility())
                .withFirstname(individualClient.getFirstname())
                .withName(individualClient.getName())
                .build();
    }

    private static ProfessionalClient toPro(ClientEntity client) {
        ProfessionalClientEntity individualClient = (ProfessionalClientEntity) client;
        return new ProfessionalClient.Builder(individualClient.getReferenceClient())
                .withId(client.getId())
                .withCompanyName(individualClient.getCompanyName())
                .withAnnualSales(individualClient.getAnnualSales())
                .withSiretNo(individualClient.getSiretNo())
                .build();
    }
}
