package roomescape.external.dto;

import roomescape.dto.request.reservation.RegularReservationPreservationRequest;

public record PaymentRequest(
        String paymentKey,
        String orderId,
        Long amount
) {

    public static PaymentRequest from(RegularReservationPreservationRequest request) {
        return new PaymentRequest(
                request.paymentKey(),
                request.orderId(),
                request.amount()
        );
    }
}
