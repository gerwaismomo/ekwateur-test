package com.gerwais.ekwateurkata.domain.model;

import com.gerwais.ekwateurkata.domain.error.BadInputException;
import com.gerwais.ekwateurkata.domain.model.client.Civilite;

import java.util.Arrays;
import java.util.Optional;

import static java.util.Objects.isNull;

public enum EnergyType {
    ELEC,
    GAS;

    public static EnergyType map(String val) {
        if (isNull(val))
            throw new BadInputException("Null energy type");

        Optional<EnergyType> matching = Arrays.stream(EnergyType.values())
                .filter(v -> v.name().equals(val))
                .findFirst();

        if (matching.isEmpty())
            throw new BadInputException("Bad energy type");
        return matching.get();
    }
}
