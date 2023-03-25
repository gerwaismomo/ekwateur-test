package com.gerwais.ekwateurkata.domain.usecase.billing;

import com.gerwais.ekwateurkata.domain.error.BadInputException;
import com.gerwais.ekwateurkata.domain.error.ConflictBusinessException;
import com.gerwais.ekwateurkata.domain.error.InternalException;
import com.gerwais.ekwateurkata.domain.error.NotFoundBusinessException;
import com.gerwais.ekwateurkata.domain.model.billing.Bill;
import com.gerwais.ekwateurkata.domain.model.client.Client;
import com.gerwais.ekwateurkata.domain.usecase.billing.port.BillProcessingUsecase;
import com.gerwais.ekwateurkata.domain.usecase.billing.port.BillSavePort;
import com.gerwais.ekwateurkata.domain.usecase.billing.port.ClientFetchPort;
import com.gerwais.ekwateurkata.domain.usecase.billing.price.Price;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.isNull;

public class BillProcessingUsecaseHandler implements BillProcessingUsecase {

    static Logger log = LoggerFactory.getLogger(BillProcessingUsecaseHandler.class);

    private final ClientFetchPort clientFetchPort;
    private final BillSavePort billSavePort;
    private Client client;
    private Bill bill;

    public BillProcessingUsecaseHandler(ClientFetchPort clientFetchPort, BillSavePort billSavePort) {
        this.clientFetchPort = clientFetchPort;
        this.billSavePort = billSavePort;
    }

    public Bill apply(BillProcessCommand command) {
        checkCommand(command);
        checkClientExists(command.getReferenceClient());
        checkNoMatchingBillExists(command);

        processBill(command);
        return saveBill();
    }

    private void checkCommand(BillProcessCommand command) {
        if (isNull(command) || isNull(command.getReferenceClient()))
            throw new BadInputException("Bad input");

    }

    private void checkClientExists(String referenceClient) {
        Optional<Client> optionalClient = clientFetchPort.findByReferenceClient(referenceClient);
        if (optionalClient.isEmpty())
            throw new NotFoundBusinessException("No client with reference exists");
        client = optionalClient.get();
    }

    private void checkNoMatchingBillExists(BillProcessCommand command) {
        boolean sameBillExists = billSavePort.billOfMonthExists(command.getReferenceClient(), command.getYear(), command.getMonth());
        if (sameBillExists)
            throw new ConflictBusinessException("Another bill exists for same client");
    }

    private void processBill(BillProcessCommand command) {
        final Price price = Price.getPriceByClient(client);

        BigDecimal total = command.getConsumptions().stream()
                .filter(Objects::nonNull)
                .map(c -> compute(c.getQuantity(), price))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        bill = new Bill(client, command.getYear(), command.getMonth(), total);
    }

    private BigDecimal compute(int consumption, Price price) {
        return BigDecimal.valueOf(consumption).multiply(BigDecimal.valueOf(price.getValue()));
    }

    private Bill saveBill() {
        try {
            return billSavePort.save(bill);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new InternalException("Failed to save bill");
        }
    }
}

