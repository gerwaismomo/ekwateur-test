package com.gerwais.ekwateurkata.domain.model.billing;

import com.gerwais.ekwateurkata.domain.model.EnergyType;

public class Consumption {

    private EnergyType energyType;

    private int quantity;

    public Consumption(EnergyType energyType, int quantity) {
        this.energyType = energyType;
        this.quantity = quantity;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }


    public int getQuantity() {
        return quantity;
    }

    public static class Builder {
        private EnergyType energyType;

        private int consumption;

        public Builder withEnergyType(EnergyType type) {
            this.energyType = type;
            return this;
        }

        public Builder withConsumption(int consumption) {
            this.consumption = consumption;
            return this;
        }

        public Consumption build() {
            return new Consumption(this.energyType, this.consumption);
        }
    }
}
