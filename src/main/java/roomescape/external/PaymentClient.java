package roomescape.external;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Encoder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import roomescape.config.PaymentClientConfig;
import roomescape.config.properties.PaymentProperties;
import roomescape.external.dto.PaymentRequest;
import roomescape.external.dto.PaymentResponse;
import roomescape.external.exception.PaymentErrorHandler;

@Component
@Import(PaymentClientConfig.class)
@EnableConfigurationProperties(PaymentProperties.class)
public class PaymentClient {

    private static final Encoder ENCODER = Base64.getEncoder();
    private static final String BASIC = "Basic ";
    private static final String ERROR_HEADER = "TossPayments-Test-Code";

    private final RestClient restClient;
    private final PaymentProperties paymentProperties;
    private final PaymentErrorHandler paymentErrorHandler;

    public PaymentClient(final RestClient restClient, final PaymentProperties paymentProperties,
                         final PaymentErrorHandler paymentErrorHandler) {
        this.restClient = restClient;
        this.paymentProperties = paymentProperties;
        this.paymentErrorHandler = paymentErrorHandler;
    }

    public PaymentResponse approvePayment(final PaymentRequest paymentRequest) {
        String widgetSecretKey = paymentProperties.getSecretKey();
        String encodedSecretKey = ENCODER.encodeToString(widgetSecretKey.getBytes(StandardCharsets.UTF_8));
        String secretKey = BASIC + encodedSecretKey;
        return restClient.post()
                .uri("/v1/payments/confirm")
                .header(HttpHeaders.AUTHORIZATION, secretKey)
//                .header(ERROR_HEADER, "EXCEED_MAX_CARD_INSTALLMENT_PLAN")
                .contentType(MediaType.APPLICATION_JSON)
                .body(paymentRequest)
                .retrieve()
                .onStatus(paymentErrorHandler)
                .body(PaymentResponse.class);
    }
}
