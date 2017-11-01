package auth;

import org.jasig.cas.client.validation.Cas30ServiceTicketValidator;

public class SfuCasTicketValidator extends Cas30ServiceTicketValidator {

    public SfuCasTicketValidator(String casServerUrlPrefix) {
        super(casServerUrlPrefix);
    }

    @Override
    protected String getUrlSuffix() {
        return "cas/serviceValidate";
    }
}
