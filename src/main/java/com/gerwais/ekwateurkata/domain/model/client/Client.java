package com.gerwais.ekwateurkata.domain.model.client;

import java.util.Objects;

public abstract class Client {
    private Long id;
    private String referenceClient;
    private ClientType clientType;

    protected Client(ClientType clientType) {
        this.clientType = clientType;
    }

    protected Client(String referenceClient, ClientType clientType) {
        this(clientType);
        this.referenceClient = referenceClient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceClient() {
        return referenceClient;
    }

    public void setReferenceClient(String referenceClient) {
        this.referenceClient = referenceClient;
    }

    public ClientType getClientType() {
        return clientType;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return referenceClient.equals(client.referenceClient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(referenceClient);
    }
}
