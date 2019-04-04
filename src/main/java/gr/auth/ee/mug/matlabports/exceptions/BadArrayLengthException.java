package gr.auth.ee.mug.matlabports.exceptions;

import javax.annotation.Nullable;


@SuppressWarnings({"unused", "WeakerAccess"})
public class BadArrayLengthException extends Exception {
    public BadArrayLengthException() {
        super();
    }

    public BadArrayLengthException(@Nullable String message) {
        super(message);
    }
}
