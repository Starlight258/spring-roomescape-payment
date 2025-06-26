package roomescape.external.exception;

import java.util.Arrays;

public enum UnRecoverableErrorCode {

    PROVIDER_ERROR,
    INVALID_API_KEY,
    NOT_FOUND_TERMINAL_ID,
    CARD_PROCESSING_ERROR,
    UNAUTHORIZED_KEY,
    INCORRECT_BASIC_AUTH_FORMAT,
    FAILED_INTERNAL_SYSTEM_PROCESSING,
    UNKNOWN_PAYMENT_ERROR;

    public static boolean contains(final String code) {
        return Arrays.stream(UnRecoverableErrorCode.values())
                .anyMatch(unRecoverableErrorCode -> unRecoverableErrorCode.name().equals(code));
    }
}
