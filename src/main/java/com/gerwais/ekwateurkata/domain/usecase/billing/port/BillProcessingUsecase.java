package com.gerwais.ekwateurkata.domain.usecase.billing.port;

import com.gerwais.ekwateurkata.domain.model.billing.Bill;
import com.gerwais.ekwateurkata.domain.usecase.billing.BillProcessCommand;

public interface BillProcessingUsecase {

    Bill apply(BillProcessCommand command);
}
