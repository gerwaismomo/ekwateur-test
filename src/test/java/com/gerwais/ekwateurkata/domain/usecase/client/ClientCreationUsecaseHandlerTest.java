package com.gerwais.ekwateurkata.domain.usecase.client;

import com.gerwais.ekwateurkata.domain.error.EkwateurException;
import com.gerwais.ekwateurkata.domain.model.client.Client;
import com.gerwais.ekwateurkata.domain.model.client.IndividualClient;
import com.gerwais.ekwateurkata.domain.error.BadInputException;
import com.gerwais.ekwateurkata.domain.error.ConflictBusinessException;
import com.gerwais.ekwateurkata.domain.error.InternalException;
import com.gerwais.ekwateurkata.domain.usecase.client.port.ClientReadWritePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(SpringExtension.class)
class ClientCreationUsecaseHandlerTest {

    @Mock
    private ClientReadWritePort repository;

    @InjectMocks
    ClientCreationUsecaseHandler handler;
    @Captor
    ArgumentCaptor<Client> clientArgumentCaptor;

    UserCreationCommand command;
    private Client newClient;

    @BeforeEach
    void setUp() {
        command = null;
    }

    @Test
    void apply_shouldThrow_whenNullCommand() {
        givenNullCommand();
        thenThrowsWhenApplyInvoked(BadInputException.class);
    }

    @Test
    void apply_shouldThrow_whenNullClientPassed() {
        givenWithNullClientCommand();

        thenThrowsWhenApplyInvoked(BadInputException.class);
    }

    @Test
    void apply_shouldThrow_whenUserWithSameRefExists() {
        givenCommandWithClient();
        givenClientWithSameReferenceExists(true);

        thenThrowsWhenApplyInvoked(ConflictBusinessException.class);
    }

    @Test
    void apply_shouldThrow_whenNoUserWithSameRefExistsAndSavingFails() {
        givenClientWithSameReferenceExists(false);
        givenCommandWithClient();
        givenRepoWillFail();

        thenThrowsWhenApplyInvoked(InternalException.class);
    }

    @Test
    void apply_shouldReturnClient_whenNoUserWithSameRefExistsAndSavingSucceed() {
        givenClientWithSameReferenceExists(false);
        givenRepoWillSave();
        givenCommandWithClient();

        whenApplyInvoked();

        thenRepoSavesAndReturnWithId();
    }






    private void givenClientWithSameReferenceExists(boolean exists) {
        given(repository.existsByReferenceClient(any())).willReturn(exists);
    }

    private void givenRepoWillSave() {
        Client client = new IndividualClient("ref");
        client.setId(1L);
        given(repository.save(clientArgumentCaptor.capture())).willReturn(client);
    }

    private void givenRepoWillFail() {
        Client client = new IndividualClient("ref");
        client.setId(1L);
        given(repository.save(clientArgumentCaptor.capture())).willThrow(RuntimeException.class);
    }


    private void givenNullCommand() {
        command = null;
    }
    
    private void givenWithNullClientCommand() {
        command = new UserCreationCommand(null);
    }

    private void givenCommandWithClient() {
        Client client = new IndividualClient("ref");
        command = new UserCreationCommand(client);
    }

    private void whenApplyInvoked() {
        newClient = handler.apply(command);
    }

    private <T extends EkwateurException> void thenThrowsWhenApplyInvoked(Class<T> exceptionClass) {
        assertThrows(exceptionClass, () -> handler.apply(command));
    }

    private void thenRepoSavesAndReturnWithId() {
        Client clientArg = clientArgumentCaptor.getValue();
        assertEquals(command.getClient().getReferenceClient(), clientArg.getReferenceClient());
        assertNotNull(newClient.getId());
    }

}