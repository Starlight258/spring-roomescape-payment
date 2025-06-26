package roomescape.config.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "external.payment")
@Getter
public class PaymentProperties {

    private final String secretKey;

    public PaymentProperties(final String secretKey) {
        this.secretKey = secretKey;
    }
}
