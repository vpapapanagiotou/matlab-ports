package gr.auth.ee.mug.matlabports.exceptions;

import javax.annotation.Nullable;


@SuppressWarnings({"unused", "WeakerAccess"})
public class LengthMismatchException extends RuntimeException {
    public LengthMismatchException() {
        super();
    }

    public LengthMismatchException(@Nullable String message) {
        super(message);
    }
}
