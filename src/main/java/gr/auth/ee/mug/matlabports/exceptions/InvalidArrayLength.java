package gr.auth.ee.mug.matlabports.exceptions;

import javax.annotation.Nullable;


@SuppressWarnings({"unused", "WeakerAccess"})
public class InvalidArrayLength extends RuntimeException {
    public InvalidArrayLength() {
        super();
    }

    public InvalidArrayLength(@Nullable String message) {
        super(message);
    }
}
