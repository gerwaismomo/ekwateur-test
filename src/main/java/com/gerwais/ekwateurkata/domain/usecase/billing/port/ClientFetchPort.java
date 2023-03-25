package com.gerwais.ekwateurkata.domain.usecase.billing.port;

import com.gerwais.ekwateurkata.domain.model.client.Client;

import java.util.Optional;

public interface ClientFetchPort {

    Optional<Client> findByReferenceClient(String referenceClient);

}
