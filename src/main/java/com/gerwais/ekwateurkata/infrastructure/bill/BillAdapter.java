package com.gerwais.ekwateurkata.infrastructure.bill;

import com.gerwais.ekwateurkata.domain.model.billing.Bill;
import com.gerwais.ekwateurkata.domain.usecase.billing.port.BillSavePort;
import com.gerwais.ekwateurkata.infrastructure.bill.entity.BillEntity;
import com.gerwais.ekwateurkata.infrastructure.bill.mapper.BillMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BillAdapter implements BillSavePort {

    private final BillRepository repository;

    public BillAdapter(BillRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean billOfMonthExists(String referenceClient, int year, int month) {
        return repository.existsByReferenceClientAndYearAndMonth(referenceClient, year, month);
    }

    @Override
    public Bill save(Bill bill) {
        BillEntity entity = BillMapper.toEntity(bill);
        entity = repository.save(entity);
        return BillMapper.toDomain(entity, bill.getClient());
    }
}
