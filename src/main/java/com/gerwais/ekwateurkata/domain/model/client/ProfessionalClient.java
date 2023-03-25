package com.gerwais.ekwateurkata.domain.model.client;

public class ProfessionalClient extends Client {

    private String siretNo;
    private String companyName;
    private Long annualSales;

    public ProfessionalClient() {
        super(ClientType.PRO);
    }

    public String getSiretNo() {
        return siretNo;
    }

    public void setSiretNo(String siretNo) {
        this.siretNo = siretNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String comparnyName) {
        this.companyName = comparnyName;
    }

    public Long getAnnualSales() {
        return annualSales;
    }

    public void setAnnualSales(Long annualSales) {
        this.annualSales = annualSales;
    }

    public static class Builder {
        private final String referenceClient;
        private Long id;
        private String siretNo;
        private String companyName;
        private Long annualSales;

        public Builder(String referenceClient) {
            this.referenceClient = referenceClient;
        }

        public Builder withSiretNo(String siretNo) {
            this.siretNo = siretNo;
            return this;
        }

        public Builder withCompanyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public Builder withAnnualSales(Long annualSales) {
            this.annualSales = annualSales;
            return this;
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public ProfessionalClient build() {
            ProfessionalClient pro = new ProfessionalClient();
            pro.setId(this.id);
            pro.setReferenceClient(referenceClient);
            pro.setCompanyName(companyName);
            pro.setAnnualSales(annualSales);
            pro.setSiretNo(siretNo);
            return pro;
        }

    }
}
