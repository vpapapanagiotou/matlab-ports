package gr.auth.ee.mug.matlabports;

import javax.annotation.Nonnull;

import gr.auth.ee.mug.matlabports.exceptions.LengthMismatchException;


/**
 * A set of common checks.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public final class Checks {

    public static boolean checkEqualLength(@Nonnull double[] x, @Nonnull double[] y) {
        return checkEqualLength(x, y, true);
    }

    public static boolean checkEqualLength(@Nonnull boolean[] x, @Nonnull boolean[] y) {
        return checkEqualLength(x, y, true);
    }

    public static boolean checkEqualLength(@Nonnull double[] x, @Nonnull double[] y, boolean abort) {
        if (x.length == y.length) {
            return true;
        } else if (abort) {
            throw new LengthMismatchException();
        } else {
            return false;
        }
    }

    public static boolean checkEqualLength(@Nonnull boolean[] x, @Nonnull boolean[] y, boolean abort) {
        if (x.length == y.length) {
            return true;
        } else if (abort) {
            throw new LengthMismatchException();
        } else {
            return false;
        }
    }

    public static boolean checkEqualLength(@Nonnull double[] x, @Nonnull boolean[] y) {
        return checkEqualLength(x, y, true);
    }

    public static boolean checkEqualLength(@Nonnull double[] x, @Nonnull boolean[] y, boolean abort) {
        if (x.length == y.length) {
            return true;
        } else if (abort) {
            throw new LengthMismatchException();
        } else {
            return false;
        }
    }

    public static boolean checkEqualLength(@Nonnull int[] x, @Nonnull boolean[] y) {
        return checkEqualLength(x, y, true);
    }

    public static boolean checkEqualLength(@Nonnull int[] x, @Nonnull boolean[] y, boolean abort) {
        if (x.length == y.length) {
            return true;
        } else if (abort) {
            throw new LengthMismatchException();
        } else {
            return false;
        }
    }
}
