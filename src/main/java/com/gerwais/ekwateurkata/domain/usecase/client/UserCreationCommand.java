package com.gerwais.ekwateurkata.domain.usecase.client;

import com.gerwais.ekwateurkata.domain.model.client.Client;

public class UserCreationCommand {
    private final Client client;

    public UserCreationCommand(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }
}
