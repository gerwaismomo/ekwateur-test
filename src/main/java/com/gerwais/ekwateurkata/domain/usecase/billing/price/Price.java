package com.gerwais.ekwateurkata.domain.usecase.billing.price;

import com.gerwais.ekwateurkata.domain.model.client.Client;
import com.gerwais.ekwateurkata.domain.model.client.ClientType;
import com.gerwais.ekwateurkata.domain.model.client.ProfessionalClient;

public abstract class Price {

    private final double value;

    protected Price(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public static Price getPriceByClient(Client client) {
        if (client.getClientType() == ClientType.INDIV) {
            return new ParticularPrice();
        } else {
            return getProPriceByAnnualSales(client);
        }
    }

    private static Price getProPriceByAnnualSales(Client client) {
        ProfessionalClient pro = (ProfessionalClient) client;
        return pro.getAnnualSales() > 1_000_000 ? new LowerProPrice() : new UpperProPrice();
    }
}

class ParticularPrice extends Price {
    private static final double PARTICULAR_PRICE = 0.121D;

    public ParticularPrice() {
        super(PARTICULAR_PRICE);
    }
}

class LowerProPrice extends Price {
    private static final double PRO_1ST_PRICE = 0.114D;

    public LowerProPrice() {
        super(PRO_1ST_PRICE);
    }
}

class UpperProPrice extends Price {
    private static final double PRO_2ND_PRICE = 0.118D;

    public UpperProPrice() {
        super(PRO_2ND_PRICE);
    }
}
