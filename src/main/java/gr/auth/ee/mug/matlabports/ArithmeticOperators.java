package gr.auth.ee.mug.matlabports;

import javax.annotation.Nonnull;

import gr.auth.ee.mug.matlabports.exceptions.LengthMismatchException;

import static gr.auth.ee.mug.matlabports.Checks.checkEqualLength;


/**
 * Arithmetic operators (addition, subtraction, etc).
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public final class ArithmeticOperators {

    /**
     * Adds two arrays element-wise.
     * <p>
     * MATLAB:
     * <pre>{@code z = x + y;}</pre>
     *
     * @param x The first input array.
     * @param y The first output array.
     * @return The sum of the two arrays z.
     */
    @Nonnull
    public static double[] add(@Nonnull double[] x, @Nonnull double[] y) throws LengthMismatchException {
        checkEqualLength(x, y);

        final int n = x.length;
        final double[] z = new double[n];
        add(x, y, z);

        return z;
    }

    /**
     * Adds two arrays element-wise.
     * <p>
     * MATLAB:
     * <pre>{@code z = x + y;}</pre>
     *
     * @param x The first input array.
     * @param y The first output array.
     * @param z The sum of the two arrays.
     */
    public static void add(@Nonnull double[] x, @Nonnull double[] y, @Nonnull double[] z)
            throws LengthMismatchException {
        checkEqualLength(x, y);
        checkEqualLength(x, z);

        for (int i = 0; i < x.length; i++) {
            z[i] = x[i] + y[i];
        }
    }

    /**
     * Adds a value to all items of an array.
     * <p>
     * MATLAB:
     * <pre>{@code x = x + v;}</pre>
     *
     * @param x The input array.
     * @param v The value to add to the array.
     * @return The ouput array.
     */
    public static double[] add(@Nonnull double[] x, double v) {
        final double[] y = new double[x.length];
        add(x, v, y);

        return y;
    }

    /**
     * Adds a value to all items of an array.
     * <p>
     * MATLAB:
     * <pre>{@code y = x + v;}</pre>
     *
     * @param x The input array.
     * @param v The value to add to the array.
     * @param y The output array.
     */
    public static void add(@Nonnull double[] x, double v, @Nonnull double[] y) {
        for (int i = 0; i < x.length; i++) {
            y[i] = x[i] + v;
        }
    }

    /**
     * Adds the machine epsilon to all items of an array.
     * <p>
     * MATLAB:
     * <pre>{@code y = x + eps();}</pre>
     *
     * @param x The input array.
     * @param y The output array.
     * @throws LengthMismatchException
     */
    public static void addEps(@Nonnull final double[] x, @Nonnull final double[] y)
            throws LengthMismatchException {
        checkEqualLength(x, y);

        for (int i = 0; i < x.length; i++) {
            y[i] = x[i] + Math.ulp(x[i]);
        }
    }

    /**
     * Adds the machine epsilon to all items of an array.
     * <p>
     * MATLAB:
     * <pre>{@code x = x + eps();}</pre>
     *
     * @param x The array to increase by epsilon.
     */
    public static void addEpsInPlace(@Nonnull final double[] x) {
        try {
            addEps(x, x);
        } catch (LengthMismatchException e) {
            // This should never happen
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a value to all items of an array. Operation is <b>in place</b>.
     * <p>
     * MATLAB:
     * <pre>{@code x = x + v;}</pre>
     *
     * @param x The array to modify.
     * @param v The value to add to the array.
     */
    public static void addInPlace(@Nonnull double[] x, double v) {
        add(x, v, x);
    }

    /**
     * Adds a value to all items of an array. Operation is <b>in place</b>.
     * <p>
     * MATLAB:
     * <pre>{@code x = x + v;}</pre>
     *
     * @param x The array to modify.
     * @param v The value to add to the array.
     */
    public static void addInPlace(@Nonnull int[] x, double v) {
        for (int i = 0; i < x.length; i++) {
            x[i] += v;
        }
    }

    /**
     * Divides two arrays element-wise.
     * <p>
     * MATLAB:
     * <pre>{@code z = x ./ y;}</pre>
     *
     * @param x The dividend array.
     * @param y The divisor array.
     * @return The quotient array.
     */
    @Nonnull
    public static double[] divide(@Nonnull double[] x, @Nonnull double[] y) throws LengthMismatchException {
        checkEqualLength(x, y);

        final double[] z = new double[x.length];
        divide(x, y, z);

        return z;
    }

    /**
     * Divides two arrays element-wise.
     * <p>
     * MATLAB:
     * <pre>{@code z = x ./ y;}</pre>
     *
     * @param x The dividend array.
     * @param y The divisor array.
     * @param z The quotient array.
     */
    public static void divide(@Nonnull double[] x, @Nonnull double[] y, @Nonnull double[] z)
            throws LengthMismatchException {
        checkEqualLength(x, y);
        checkEqualLength(x, z);

        for (int i = 0; i < z.length; i++) {
            z[i] = x[i] / y[i];
        }
    }

    /**
     * Index of maximum element of an array. If there are multiple maximum
     * elements, the index of the first is returned.
     * <p>
     * MATLAB:
     * <pre>{@code [~, idx] = max(x);}</pre>
     *
     * @param x The array
     * @return The index of the maximum element of x
     */
    public static int maxIdx(@Nonnull double[] x) {
        return maxIdx(x, 0, x.length);
    }

    /**
     * Index of maximum element of a part of an array. If there are multiple maximum
     * elements, the index of the first is returned.
     * <p>
     * MATLAB:
     * <pre>{@code [~, idx] = max(x);}</pre>
     *
     * @param x The array
     * @param start The index from which to start searching for the maximum (inclusive)
     * @param stop The index at which to stop searching for the maximum (exclusive)
     * @return The index of the maximum element of x
     */
    public static int maxIdx(@Nonnull final  double[] x, final int start, final int stop) {
        if (x.length == 0) {
            return -1;
        }
        if (x.length == 1) {
            return 0;
        }

        int idx = 0;

        for (int i = start; i < stop; i++) {
            if (x[i] > x[idx]) {
                idx = i;
            }
        }

        return idx;
    }

    /**
     * Index of minimum element of an array. If there are multiple minimum
     * elements, the index of the first is returned.
     * <p>
     * MATLAB:
     * <pre>{@code [~, idx] = min(x);}</pre>
     *
     * @param x The array
     * @return The index of the minimum element of x
     */
    public static int minIdx(@Nonnull double[] x) {
        if (x.length == 0) {
            return -1;
        }
        if (x.length == 1) {
            return 0;
        }

        int idx = 0;

        for (int i = 0; i < x.length; i++) {
            if (x[i] < x[idx]) {
                idx = i;
            }
        }

        return idx;
    }

    /**
     * Multiplies each element of an array with a value.
     * <p>
     * MATLAB:
     * <pre>{@code y = x * v;}</pre>
     *
     * @param x The input array.
     * @param v The value to multiply with.
     * @param y The resulting array.
     */
    public static void multiply(@Nonnull double[] x, double v, @Nonnull double[] y)
            throws LengthMismatchException {
        checkEqualLength(x, y);

        for (int i = 0; i < x.length; i++) {
            y[i] = v * x[i];
        }
    }

    /**
     * Multiplies each element of an array with a value. Operation is <b>in place</b>.
     * <p>
     * MATLAB:
     * <pre>{@code x = x * v;}</pre>
     *
     * @param x The array to modify.
     * @param v The value to multiply with.
     */
    public static void multiplyInPlace(@Nonnull double[] x, double v) throws LengthMismatchException {
        multiply(x, v, x);
    }

    /**
     * Multiplies each element of an array with a value. Operation is <b>in place</b>.
     * <p>
     * MATLAB:
     * <pre>{@code x = x * v;}</pre>
     *
     * @param x The array to modify.
     * @param v The value to multiply with.
     */
    public static void multiplyInPlace(@Nonnull int[] x, double v) {
        for (int i = 0; i < x.length; i++) {
            x[i] *= v;
        }
    }

    /**
     * Multiplies each element of an array with a value. Operation is <b>in place</b>.
     * <p>
     * MATLAB:
     * <pre>{@code x = x * v;}</pre>
     *
     * @param x The array to modify.
     * @param v The value to multiply with.
     */
    public static void multiplyInPlace(@Nonnull int[] x, int v) {
        for (int i = 0; i < x.length; i++) {
            x[i] *= v;
        }
    }

    /**
     * Multiplies each element of an array with a value. Operation is <b>in place</b>.
     * <p>
     * MATLAB:
     * <pre>{@code x = x * v;}</pre>
     *
     * @param x The array to modify.
     * @param y The value to multiply with.
     */
    public static void multiplyInPlace(@Nonnull double[] x, @Nonnull double[] y)
            throws LengthMismatchException {
        checkEqualLength(x, y);

        for (int i = 0; i < x.length; i++) {
            x[i] *= y[i];
        }
    }

    /**
     * Subtract two arrays element-wise.
     * <p>
     * MATLAB:
     * <pre>{@code z = x - y;}</pre>
     *
     * @param x The array to subtract from.
     * @param y The array that is subtracted.
     * @param z The resulting array.
     */
    public static void subtract(@Nonnull double[] x, @Nonnull double[] y, @Nonnull double[] z)
            throws LengthMismatchException {
        checkEqualLength(x, y);
        checkEqualLength(x, z);

        for (int i = 0; i < z.length; i++) {
            z[i] = x[i] - y[i];
        }
    }

    /**
     * Subtract two arrays element-wise.
     * <p>
     * MATLAB:
     * <pre>{@code z = x - y;}</pre>
     *
     * @param x The array to subtract from.
     * @param y The array that is subtracted.
     * @return The resulting array.
     */
    @Nonnull
    public static double[] subtract(@Nonnull double[] x, @Nonnull double[] y) throws LengthMismatchException {
        checkEqualLength(x, y);

        final double[] z = new double[x.length];
        subtract(x, y, z);
        return z;
    }
}
