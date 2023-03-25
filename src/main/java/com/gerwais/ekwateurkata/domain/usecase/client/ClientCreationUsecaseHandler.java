package com.gerwais.ekwateurkata.domain.usecase.client;

import com.gerwais.ekwateurkata.domain.model.client.Client;
import com.gerwais.ekwateurkata.domain.error.BadInputException;
import com.gerwais.ekwateurkata.domain.error.ConflictBusinessException;
import com.gerwais.ekwateurkata.domain.error.InternalException;
import com.gerwais.ekwateurkata.domain.usecase.client.port.ClientCreationUsecase;
import com.gerwais.ekwateurkata.domain.usecase.client.port.ClientReadWritePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Objects.isNull;


public class ClientCreationUsecaseHandler implements ClientCreationUsecase {
    Logger log = LoggerFactory.getLogger(ClientCreationUsecaseHandler.class);
    private final ClientReadWritePort clientReadWritePort;

    public ClientCreationUsecaseHandler(ClientReadWritePort clientReadWritePort) {
        this.clientReadWritePort = clientReadWritePort;
    }

    public Client apply(UserCreationCommand command) {
        checkInput(command);
        checkNoClientWithSameReferenceExists(command);

        return saveNewClient(command.getClient());
    }

    private void checkNoClientWithSameReferenceExists(UserCreationCommand command) {
        boolean clientExists = clientReadWritePort.existsByReferenceClient(command.getClient().getReferenceClient());
        if(clientExists)
            throw new ConflictBusinessException("Client with same reference exists");
    }

    private static void checkInput(UserCreationCommand command) {
        if(isNull(command) || isNull(command.getClient()))
            throw new BadInputException("Invalid input");
    }

    private Client saveNewClient(Client client) {
        try {
            Client newClient = this.clientReadWritePort.save(client);
            log.info("New client saved {}", newClient.getReferenceClient());
            return newClient;
        } catch (Exception e) {
            log.error("Failed to save client {}", client);
            throw new InternalException("Failed to save client {}");
        }
    }
}
