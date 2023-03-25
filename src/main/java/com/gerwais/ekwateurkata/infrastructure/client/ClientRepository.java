package com.gerwais.ekwateurkata.infrastructure.client;

import com.gerwais.ekwateurkata.infrastructure.client.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    Optional<ClientEntity> findByReferenceClient(String referenceClient);

    boolean existsByReferenceClient(String referenceClient);
}
