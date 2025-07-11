package roomescape.external.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import roomescape.exception.RecoverableExternalException;
import roomescape.exception.UnRecoverableExternalException;
import roomescape.external.dto.PaymentFailedResult;

@Component
@RequiredArgsConstructor
public class PaymentErrorHandler implements ResponseErrorHandler {

    private final ObjectMapper objectMapper;

    @Override
    public boolean hasError(final ClientHttpResponse response) throws IOException {
        HttpStatusCode statusCode = response.getStatusCode();
        return statusCode.is4xxClientError() || statusCode.is5xxServerError();
    }

    @Override
    public void handleError(final URI url, final HttpMethod method, final ClientHttpResponse response)
            throws IOException {
        PaymentFailedResult paymentFailedResult = objectMapper.readValue(response.getBody(), PaymentFailedResult.class);
        if (UnRecoverableErrorCode.contains(paymentFailedResult.code())) {
            throw new UnRecoverableExternalException(paymentFailedResult);
        }
        throw new RecoverableExternalException(paymentFailedResult);
    }
}
