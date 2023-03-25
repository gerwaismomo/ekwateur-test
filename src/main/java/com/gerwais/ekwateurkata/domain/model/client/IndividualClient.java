package com.gerwais.ekwateurkata.domain.model.client;

public class IndividualClient extends Client {
    private Civilite civility;
    private String name;
    private String firstname;

    public IndividualClient() {
        super(ClientType.INDIV);
    }

    public IndividualClient(String referenceClient) {
        super(referenceClient, ClientType.INDIV);
    }

    public Civilite getCivility() {
        return civility;
    }

    public void setCivility(Civilite civility) {
        this.civility = civility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public static class Builder {
        private final String referenceClient;
        private Long id;
        private Civilite civility;
        private String name;
        private String firstname;

        public Builder(String referenceClient) {
            this.referenceClient = referenceClient;
        }

        public Builder withCivility(Civilite civility) {
            this.civility = civility;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public IndividualClient build() {
            IndividualClient pro = new IndividualClient();
            pro.setReferenceClient(referenceClient);
            pro.setId(id);
            pro.setCivility(civility);
            pro.setFirstname(firstname);
            pro.setName(name);
            return pro;
        }

    }
}
