package com.gerwais.ekwateurkata.domain.usecase.client.port;

import com.gerwais.ekwateurkata.domain.model.client.Client;
import com.gerwais.ekwateurkata.domain.usecase.client.UserCreationCommand;

public interface ClientCreationUsecase {
    Client apply(UserCreationCommand command);
}
