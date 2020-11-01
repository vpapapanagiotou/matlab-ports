package gr.auth.ee.mug.matlabports;

import javax.annotation.Nonnull;

import gr.auth.ee.mug.matlabports.exceptions.LengthMismatchException;

import static gr.auth.ee.mug.matlabports.Checks.checkEqualLength;


/**
 * Logical operators (and, or, not, etc).
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public final class LogicalOperators {

    /**
     * Logical and between two 1D arrays.
     * <p>
     * MATLAB:
     * <pre>{@code z = x && y;}</pre>
     *
     * @param x The first array.
     * @param y The second array.
     * @return A new array containing the result.
     */
    @Nonnull
    public static boolean[] and(@Nonnull boolean[] x, @Nonnull boolean[] y) throws LengthMismatchException {
        checkEqualLength(x, y);

        final boolean[] z = new boolean[x.length];
        for (int i = 0; i < x.length; i++) {
            z[i] = x[i] && y[i];
        }

        return z;
    }

    /**
     * Check if array elements are equal to a specific value.
     * <p>
     * MATLAB:
     * <pre>{@code b = y == v;}</pre>
     *
     * @param x The array to check its values.
     * @param v The value to compare to.
     * @return A boolean array with the comparison results.
     */
    public static boolean[] isEqual(@Nonnull double[] x, double v) {
        @Nonnull final boolean[] b = new boolean[x.length];

        for (int i = 0; i < x.length; i++) {
            b[i] = x[i] == v;
        }

        return b;
    }

    /**
     * Check if array elements are equal to a specific value.
     * <p>
     * MATLAB:
     * <pre>{@code b = y == v;}</pre>
     *
     * @param x The array to check its values.
     * @param v The value to compare to.
     * @return A boolean array with the comparison results.
     */
    public static boolean[] isEqual(@Nonnull long[] x, long v) {
        @Nonnull final boolean[] b = new boolean[x.length];

        for (int i = 0; i < x.length; i++) {
            b[i] = x[i] == v;
        }

        return b;
    }

    /**
     * Compares an array with a value using the less operator.
     * <p>
     * MATLAB:
     * <pre>{@code b = x < v;}</pre>
     *
     * @param x The input array.
     * @param v The value to compare to.
     * @return A boolean array that is true for the elements of x that are less than v.
     */
    @Nonnull
    public static boolean[] less(@Nonnull double[] x, double v) {
        final boolean[] b = new boolean[x.length];
        for (int i = 0; i < x.length; i++) {
            b[i] = x[i] < v;
        }

        return b;
    }

    /**
     * Compares an array with a value using the less operator.
     * <p>
     * MATLAB:
     * <pre>{@code b = x < v;}</pre>
     *
     * @param x The input array.
     * @param v The value to compare to.
     * @return A boolean array that is true for the elements of x that are less than v.
     */
    @Nonnull
    public static boolean[] less(@Nonnull int[] x, double v) {
        final boolean[] b = new boolean[x.length];
        for (int i = 0; i < x.length; i++) {
            b[i] = x[i] < v;
        }

        return b;
    }

    /**
     * Compares an array with a value using the less or equal operator.
     * <p>
     * MATLAB:
     * <pre>{@code b = x -< v;}</pre>
     *
     * @param x The input array.
     * @param v The value to compare to.
     * @return A boolean array that is true for the elements of x that are less than or equal to v.
     */
    @Nonnull
    public static boolean[] lesseq(@Nonnull double[] x, double v) {
        final boolean[] b = new boolean[x.length];
        for (int i = 0; i < x.length; i++) {
            b[i] = x[i] <= v;
        }

        return b;
    }

    /**
     * Compares an array with a value using the less or equal operator.
     * <p>
     * MATLAB:
     * <pre>{@code b = x <= v;}</pre>
     *
     * @param x The input array.
     * @param v The value to compare to.
     * @return A boolean array that is true for the elements of x that are less than or equal to v.
     */
    @Nonnull
    public static boolean[] lesseq(@Nonnull int[] x, double v) {
        final boolean[] b = new boolean[x.length];
        for (int i = 0; i < x.length; i++) {
            b[i] = x[i] <= v;
        }

        return b;
    }

    /**
     * Compares an array with a value using the more operator.
     * <p>
     * MATLAB:
     * <pre>{@code b = x > v;}</pre>
     *
     * @param x The input array (double).
     * @param v The value to compare to.
     * @return A boolean array that is true for the elements of x that are more than v.
     */
    @Nonnull
    public static boolean[] more(@Nonnull double[] x, double v) {
        final boolean[] b = new boolean[x.length];
        for (int i = 0; i < x.length; i++) {
            b[i] = x[i] > v;
        }

        return b;
    }

    /**
     * Compares an array with a value using the more operator.
     * <p>
     * MATLAB:
     * <pre>{@code b = x > v;}</pre>
     *
     * @param x The input array.
     * @param v The value to compare to.
     * @return A boolean array that is true for the elements of x that are more than v.
     */
    @Nonnull
    public static boolean[] more(@Nonnull int[] x, double v) {
        final boolean[] b = new boolean[x.length];
        for (int i = 0; i < x.length; i++) {
            b[i] = x[i] > v;
        }

        return b;
    }

    /**
     * Compares an array with a value using the more or equal operator.
     * <p>
     * MATLAB:
     * <pre>{@code b = x >= v;}</pre>
     *
     * @param x The input array (double).
     * @param v The value to compare to.
     * @return A boolean array that is true for the elements of x that are more than or equal to v.
     */
    @Nonnull
    public static boolean[] moreeq(@Nonnull double[] x, double v) {
        final boolean[] b = new boolean[x.length];
        for (int i = 0; i < x.length; i++) {
            b[i] = x[i] >= v;
        }

        return b;
    }

    /**
     * Compares an array with a value using the more or equal operator.
     * <p>
     * MATLAB:
     * <pre>{@code b = x >= v;}</pre>
     *
     * @param x The input array.
     * @param v The value to compare to.
     * @return A boolean array that is true for the elements of x that are more than or equal to v.
     */
    @Nonnull
    public static boolean[] moreeq(@Nonnull int[] x, double v) {
        final boolean[] b = new boolean[x.length];
        for (int i = 0; i < x.length; i++) {
            b[i] = x[i] >= v;
        }

        return b;
    }

    /**
     * Applies the not operator.
     * <p>
     * MATLAB:
     * <pre>{@code nb = ~b;}</pre>
     *
     * @param b The input array.
     * @return A boolean array with the oposive values of the input array.
     */
    @Nonnull
    public static boolean[] not(@Nonnull boolean[] b) {
        final boolean[] nb = new boolean[b.length];

        try {
            not(b, nb);
        } catch (LengthMismatchException e) {
            // This sound never happen since we created nb to have equal length to b
            throw new RuntimeException(e);
        }

        return nb;
    }

    /**
     * Applies the not operator on the input array and writes the result on the output array.
     * <p>
     * MATLAB:
     * <pre>{@code out = ~in;}</pre>
     *
     * @param in  The input array.
     * @param out The output array.
     */
    public static void not(@Nonnull boolean[] in, @Nonnull boolean[] out) throws LengthMismatchException {
        checkEqualLength(in, out);

        for (int i = 0; i < in.length; i++) {
            out[i] = !in[i];
        }
    }

    /**
     * Applies the not operator on a boolean array <b>in place</b>.
     * <p>
     * MATLAB:
     * <pre>{@code b = ~b;}</pre>
     *
     * @param b The boolean array.
     */
    public static void notInPlace(@Nonnull boolean[] b) {
        try {
            not(b, b);
        } catch (LengthMismatchException e) {
            // This should never happen, since we pass the same array to not
            throw new RuntimeException(e);
        }
    }
}
