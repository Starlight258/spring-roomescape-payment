package roomescape.exception;

import lombok.Getter;
import roomescape.external.dto.PaymentFailedResult;

@Getter
public class RecoverableExternalException extends RoomescapeException {

    private final PaymentFailedResult paymentFailedResult;

    public RecoverableExternalException(final PaymentFailedResult paymentFailedResult) {
        super(paymentFailedResult.message());
        this.paymentFailedResult = paymentFailedResult;
    }
}
