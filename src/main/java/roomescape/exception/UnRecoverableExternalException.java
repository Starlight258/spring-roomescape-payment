package roomescape.exception;

import lombok.Getter;
import roomescape.external.dto.PaymentFailedResult;

@Getter
public class UnRecoverableExternalException extends RoomescapeException {

    private final PaymentFailedResult paymentFailedResult;

    public UnRecoverableExternalException(final PaymentFailedResult paymentFailedResult) {
        super(paymentFailedResult.message());
        this.paymentFailedResult = paymentFailedResult;
    }
}
