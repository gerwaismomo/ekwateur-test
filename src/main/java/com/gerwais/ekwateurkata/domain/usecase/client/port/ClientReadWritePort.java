package com.gerwais.ekwateurkata.domain.usecase.client.port;

import com.gerwais.ekwateurkata.domain.model.client.Client;

public interface ClientReadWritePort {
    boolean existsByReferenceClient(String referenceClient);
    Client save(Client client);
}
