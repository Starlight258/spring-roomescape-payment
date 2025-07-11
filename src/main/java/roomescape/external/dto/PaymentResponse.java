package roomescape.external.dto;

import roomescape.domain.payment.Payment;

public record PaymentResponse(String paymentKey, Long totalAmount) {

    public Payment toPayment() {
        return new Payment(paymentKey, totalAmount);
    }
}
