package com.gerwais.ekwateurkata.domain.model.client;

import com.gerwais.ekwateurkata.domain.error.BadInputException;

import java.util.Arrays;
import java.util.Optional;

import static java.util.Objects.isNull;

public enum Civilite {
    M,
    Mme,
    Mlle;

    public static Civilite map(String val) {
        if (isNull(val))
            throw new BadInputException("Null civility");

        Optional<Civilite> matching = Arrays.stream(Civilite.values())
                .filter(v -> v.name().equals(val))
                .findFirst();

        if (matching.isEmpty())
            throw new BadInputException("Bad civility");
        return matching.get();
    }
}
