package com.gerwais.ekwateurkata.application;

import com.gerwais.ekwateurkata.domain.usecase.billing.BillProcessingUsecaseHandler;
import com.gerwais.ekwateurkata.domain.usecase.billing.port.BillProcessingUsecase;
import com.gerwais.ekwateurkata.domain.usecase.billing.port.BillSavePort;
import com.gerwais.ekwateurkata.domain.usecase.billing.port.ClientFetchPort;
import com.gerwais.ekwateurkata.domain.usecase.client.ClientCreationUsecaseHandler;
import com.gerwais.ekwateurkata.domain.usecase.client.port.ClientCreationUsecase;
import com.gerwais.ekwateurkata.domain.usecase.client.port.ClientReadWritePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ClientCreationUsecase clientCreationUsecase(ClientReadWritePort clientReadWritePort) {
        return new ClientCreationUsecaseHandler(clientReadWritePort);
    }

    @Bean
    public BillProcessingUsecase billProcessingUsecase(ClientFetchPort clientFetchPort, BillSavePort billSavePort) {
        return new BillProcessingUsecaseHandler(clientFetchPort, billSavePort);
    }

}
