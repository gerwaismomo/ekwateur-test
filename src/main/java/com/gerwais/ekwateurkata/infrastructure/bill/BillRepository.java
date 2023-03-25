package com.gerwais.ekwateurkata.infrastructure.bill;

import com.gerwais.ekwateurkata.infrastructure.bill.entity.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<BillEntity, Long> {

    public boolean existsByReferenceClientAndYearAndMonth(String referenceClient, int year, int month);
}
