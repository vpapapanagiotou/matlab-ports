package gr.auth.ee.mug.matlabports.exceptions;

import javax.annotation.Nullable;


public class UnknownTimeUnitException extends Exception {

    public UnknownTimeUnitException() {
        super();
    }

    public UnknownTimeUnitException(@Nullable String message) {
        super(message);
    }
}
