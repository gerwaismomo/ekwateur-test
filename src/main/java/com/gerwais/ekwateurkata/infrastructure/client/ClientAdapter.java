package com.gerwais.ekwateurkata.infrastructure.client;

import com.gerwais.ekwateurkata.domain.model.client.Client;
import com.gerwais.ekwateurkata.domain.usecase.billing.port.ClientFetchPort;
import com.gerwais.ekwateurkata.domain.usecase.client.port.ClientReadWritePort;
import com.gerwais.ekwateurkata.infrastructure.client.entity.ClientEntity;
import com.gerwais.ekwateurkata.infrastructure.client.mapper.ClientEntityMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientAdapter implements ClientFetchPort, ClientReadWritePort {

    private final ClientRepository repository;

    public ClientAdapter(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Client> findByReferenceClient(String referenceClient) {
        return repository.findByReferenceClient(referenceClient).map(ClientEntityMapper::toDomain);
    }


    @Override
    public boolean existsByReferenceClient(String referenceClient) {
        return repository.existsByReferenceClient(referenceClient);
    }

    @Override
    public Client save(Client client) {
        ClientEntity entity = ClientEntityMapper.toEntity(client);
        entity = repository.save(entity);
        return ClientEntityMapper.toDomain(entity);
    }
}
