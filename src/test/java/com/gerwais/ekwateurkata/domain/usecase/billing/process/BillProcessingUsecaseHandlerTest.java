package com.gerwais.ekwateurkata.domain.usecase.billing.process;

import com.gerwais.ekwateurkata.domain.error.*;
import com.gerwais.ekwateurkata.domain.model.EnergyType;
import com.gerwais.ekwateurkata.domain.model.billing.Bill;
import com.gerwais.ekwateurkata.domain.model.billing.Consumption;
import com.gerwais.ekwateurkata.domain.model.client.Client;
import com.gerwais.ekwateurkata.domain.model.client.IndividualClient;
import com.gerwais.ekwateurkata.domain.usecase.billing.BillProcessCommand;
import com.gerwais.ekwateurkata.domain.usecase.billing.BillProcessingUsecaseHandler;
import com.gerwais.ekwateurkata.domain.usecase.billing.port.BillSavePort;
import com.gerwais.ekwateurkata.domain.usecase.billing.port.ClientFetchPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class BillProcessingUsecaseHandlerTest {

    public static final String REF_CLIENT = "EKW12345678";
    @Mock
    ClientFetchPort clientFetchPort;
    @Mock
    BillSavePort billSavePort;

    @InjectMocks
    BillProcessingUsecaseHandler handler;
    @Captor
    ArgumentCaptor<Bill> billArgumentCaptor;
    private BillProcessCommand command;
    private Bill savedBill;


    @Test
    void apply_shouldThrow_whenCommandIsNull() {
        givenNullCommand();
        thenThrowsWhenApplyInvoked(BadInputException.class);
    }

    @Test
    void apply_shouldThrow_whenNoClientExists() {
        givenCommandWithClient();
        givenRepoHasNoMatchingClient();

        thenThrowsWhenApplyInvoked(NotFoundBusinessException.class);
    }

    @Test
    void apply_shouldThrow_whenMatchingBillExists() {
        givenCommandWithClient();
        givenRepoHasMatchingClient();
        givenRepoHasSameBill(true);

        thenThrowsWhenApplyInvoked(ConflictBusinessException.class);
    }

    @Test
    void apply_shouldThrow_whenRepoFailedToPersist() {
        givenCommandWithClient();
        givenRepoHasMatchingClient();
        givenRepoHasSameBill(false);
        givenRepoWillFailToSaveBill();

        thenThrowsWhenApplyInvoked(InternalException.class);
    }

    @Test
    void apply_shouldReturnBill_whenAllSucceeded() {
        givenCommandWithClient();
        givenRepoHasMatchingClient();
        givenRepoHasSameBill(false);
        givenRepoWillSaveBill();

        whenApplyInvoked();

        thenRepoSavesAndReturnWithId();
    }


    private void givenNullCommand() {
        command = null;
    }

    private void givenCommandWithClient() {
        String refClient = REF_CLIENT;
        int year = 2023;
        int month = 3;
        Consumption cons1 = new Consumption(EnergyType.ELEC, 1000);
        Consumption cons2 = new Consumption(EnergyType.GAS, 10000);
        List<Consumption> allCons = List.of(cons1, cons2);
        command = new BillProcessCommand(refClient, year, month, allCons);
    }

    private void givenRepoHasNoMatchingClient() {
        given(clientFetchPort.findByReferenceClient(any())).willReturn(Optional.empty());
    }

    private void givenRepoHasMatchingClient() {
        Client client = new IndividualClient(REF_CLIENT);
        client.setId(1L);
        given(clientFetchPort.findByReferenceClient(any())).willReturn(Optional.of(client));
    }

    private void givenRepoHasSameBill(boolean exists) {
        given(billSavePort.billOfMonthExists(any(), anyInt(), anyInt())).willReturn(exists);
    }

    private void givenRepoWillSaveBill() {
        Client client = new IndividualClient(REF_CLIENT);
        client.setId(1L);
        Bill bill = new Bill(client, command.getYear(), command.getMonth(), BigDecimal.TEN);
        bill.setId(1L);
        given(billSavePort.save(billArgumentCaptor.capture())).willReturn(bill);
    }

    private void givenRepoWillFailToSaveBill() {
        given(billSavePort.save(billArgumentCaptor.capture())).willThrow(RuntimeException.class);
    }

    private void whenApplyInvoked() {
        savedBill = handler.apply(command);
    }

    private <T extends EkwateurException> void thenThrowsWhenApplyInvoked(Class<T> exceptionClass) {
        assertThrows(exceptionClass, () -> handler.apply(command));
    }

    private void thenRepoSavesAndReturnWithId() {
        Bill billArg = billArgumentCaptor.getValue();
        BigDecimal total = BigDecimal.valueOf(Double.valueOf("1331")).setScale(3);

        assertNotNull(billArg.getClient());
        assertEquals(command.getReferenceClient(), billArg.getClient().getReferenceClient());
        assertEquals(total, billArg.getTotal());
        assertNotNull(savedBill.getId());
    }
}