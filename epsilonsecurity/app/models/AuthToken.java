package models;

public class AuthToken {
    private String casName;

    public AuthToken(String casName) {
        this.casName = casName;
    }

    public String getCasName() {
        return casName;
    }

}
