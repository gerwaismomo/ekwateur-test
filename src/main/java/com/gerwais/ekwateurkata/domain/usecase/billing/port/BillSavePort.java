package com.gerwais.ekwateurkata.domain.usecase.billing.port;

import com.gerwais.ekwateurkata.domain.model.billing.Bill;

public interface BillSavePort {
    boolean billOfMonthExists(String referenceClient, int year, int month);

    Bill save(Bill bill);
}
