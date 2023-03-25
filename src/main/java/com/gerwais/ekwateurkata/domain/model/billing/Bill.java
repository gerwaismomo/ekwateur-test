package com.gerwais.ekwateurkata.domain.model.billing;

import com.gerwais.ekwateurkata.domain.model.client.Client;

import java.math.BigDecimal;

public class Bill {

    private Long id;
    private Client client;
    private int year;
    private int month;
    private BigDecimal total;

    public Bill(Client client, int year, int month, BigDecimal total) {
        this.client = client;
        this.year = year;
        this.month = month;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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

    public BigDecimal getTotal() {
        return total;
    }


    public static class Builder {
        private Long id;
        private Client client;
        private int year;
        private int month;
        private BigDecimal total;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withClient(Client client) {
            this.client = client;
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

        public Builder withTotal(BigDecimal total) {
            this.total = total;
            return this;
        }


        public Bill build() {
            Bill bill = new Bill(client, year, month, total);
            bill.setId(id);

            return bill;
        }
    }
}
