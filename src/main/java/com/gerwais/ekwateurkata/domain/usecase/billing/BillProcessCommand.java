package com.gerwais.ekwateurkata.domain.usecase.billing;

import com.gerwais.ekwateurkata.domain.model.billing.Consumption;

import java.util.List;

public class BillProcessCommand {
    private String referenceClient;
    private int year;
    private int month;
    List<Consumption> consumptions;

    public BillProcessCommand(String referenceClient, int year, int month, List<Consumption> consumptions) {
        this.referenceClient = referenceClient;
        this.year = year;
        this.month = month;
        this.consumptions = consumptions;
    }

    public String getReferenceClient() {
        return referenceClient;
    }

    public void setReferenceClient(String referenceClient) {
        this.referenceClient = referenceClient;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public List<Consumption> getConsumptions() {
        return consumptions;
    }

    public void setConsumptions(List<Consumption> consumptions) {
        this.consumptions = consumptions;
    }


    public static class Builder {
        private String referenceClient;
        private int year;
        private int month;
        List<Consumption> consumptions;

        public Builder withReferenceClient(String referenceClient) {
            this.referenceClient = referenceClient;
            return this;
        }

        public Builder withYear(int year) {
            this.year = year;
            return this;
        }

        public Builder withMonth(int month) {
            this.month = month;
            return this;
        }

        public Builder withConsumptions(List<Consumption> consumptions) {
            this.consumptions = consumptions;
            return this;
        }

        public BillProcessCommand build() {
            return new BillProcessCommand(this.referenceClient, this.year, this.month, this.consumptions);
        }
    }
}
