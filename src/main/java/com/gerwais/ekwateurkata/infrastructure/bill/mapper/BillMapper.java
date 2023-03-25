package com.gerwais.ekwateurkata.infrastructure.bill.mapper;

import com.gerwais.ekwateurkata.domain.model.billing.Bill;
import com.gerwais.ekwateurkata.domain.model.client.Client;
import com.gerwais.ekwateurkata.infrastructure.bill.entity.BillEntity;

public interface BillMapper {
    static BillEntity toEntity(Bill bill) {
        return BillEntity.builder()
                .id(bill.getId())
                .referenceClient(bill.getClient().getReferenceClient())
                .month(bill.getMonth())
                .year(bill.getYear())
                .total(bill.getTotal())
                .build();
    }

    static Bill toDomain(BillEntity entity, Client client) {

        return new Bill.Builder()
                .withId(entity.getId())
                .withClient(client)
                .withMonth(entity.getMonth())
                .withYear(entity.getYear())
                .withTotal(entity.getTotal())
                .build();
    }
}
