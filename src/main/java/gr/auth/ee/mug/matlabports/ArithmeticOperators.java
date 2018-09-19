package gr.auth.ee.mug.matlabports;

import javax.annotation.Nonnull;

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
    public static double[] add(@Nonnull double[] x, @Nonnull double[] y) {
        checkEqualLength(x, y);

        final int n = x.length;
        final double[] z = new double[n];
        for (int i = 0; i < n; i++) {
            z[i] = x[i] + y[i];
        }

        return z;
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
    public static double[] divide(@Nonnull double[] x, @Nonnull double[] y) {
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
    public static void divide(@Nonnull double[] x, @Nonnull double[] y, @Nonnull double[] z) {
        checkEqualLength(x, y);
        checkEqualLength(x, z);

        for (int i = 0; i < z.length; i++) {
            z[i] = x[i] / y[i];
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
    public static void multiplyInPlace(@Nonnull double[] x, double v) {
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
    public static void multiplyInPlace(@Nonnull double[] x, @Nonnull double[] y) {
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
    public static void subtract(@Nonnull double[] x, @Nonnull double[] y, @Nonnull double[] z) {
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
    public static double[] subtract(@Nonnull double[] x, @Nonnull double[] y) {
        checkEqualLength(x, y);

        final double[] z = new double[x.length];
        subtract(x, y, z);
        return z;
    }
}
