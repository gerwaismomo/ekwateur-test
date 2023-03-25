package com.gerwais.ekwateurkata.infrastructure.client.entity;


import com.gerwais.ekwateurkata.domain.model.client.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Objects;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(indexes = {
        @Index(name = "reference_client", columnList = "referenceClient")
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_discriminator")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(nullable = false)
    protected String referenceClient;
    protected ClientType clientType;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ClientEntity that = (ClientEntity) o;
        return referenceClient.equals(that.referenceClient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(referenceClient);
    }
}
