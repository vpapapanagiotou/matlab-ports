package gr.auth.ee.mug.matlabports;

import java.util.Arrays;

import javax.annotation.Nonnull;

import static gr.auth.ee.mug.matlabports.Checks.checkEqualLength;


/**
 * Common functions.
 * <p>
 * Contains element-wise functions (e.g. abs, log), reductions (e.g. sum, mean, std), and some other (e.g. diff).
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public final class CommonFunctions {

    /**
     * Computes the absolute value of each item of the array. Operation is <b>in place</b>.
     * <p>
     * MATLAB:
     * <pre>{@code x = abs(x);}</pre>
     */
    public static void absInPlace(@Nonnull double[] x) {
        for (int i = 0; i < x.length; i++) {
            x[i] = Math.abs(x[i]);
        }
    }

    /**
     * Computes the pair-wise differences.
     * <p>
     * MATLAB:
     * <pre>{@code y = diff(x);}</pre>
     *
     * @param x The input array.
     * @return The pair-wise differences array.
     */
    @Nonnull
    public static int[] diff(@Nonnull int[] x) {
        // Too short arrays are handled like this
        if (x.length <= 1) {
            return new int[0];
        }

        final int[] y = new int[x.length - 1];
        for (int i = 0; i < y.length; i++) {
            y[i] = x[i + 1] - x[i];
        }

        return y;
    }

    /**
     * Computes the pair-wise differences.
     * <p>
     * MATLAB:
     * <pre>{@code y = diff(x);}</pre>
     *
     * @param x The input array.
     * @return The pair-wise differences array.
     */
    @Nonnull
    public static long[] diff(@Nonnull long[] x) {
        // Too short arrays are handled like this
        if (x.length <= 1) {
            return new long[0];
        }

        final long[] y = new long[x.length - 1];
        for (int i = 0; i < y.length; i++) {
            y[i] = x[i + 1] - x[i];
        }

        return y;
    }

    /**
     * Computes the pair-wise differences.
     * <p>
     * MATLAB:
     * <pre>{@code y = diff(x);}</pre>
     *
     * @param x The input array.
     * @return The pair-wise differences array.
     */
    @Nonnull
    public static float[] diff(@Nonnull float[] x) {
        // Too short arrays are handled like this
        if (x.length <= 1) {
            return new float[0];
        }

        final float[] y = new float[x.length - 1];
        for (int i = 0; i < y.length; i++) {
            y[i] = x[i + 1] - x[i];
        }

        return y;
    }

    /**
     * Computes the pair-wise differences.
     * <p>
     * MATLAB:
     * <pre>{@code y = diff(x);}</pre>
     *
     * @param x The input array.
     * @return The pair-wise differences array.
     */
    @Nonnull
    public static double[] diff(@Nonnull double[] x) {
        if (x.length < 1) {
            return new double[0];
        }
        final double[] y = new double[x.length - 1];
        for (int i = 0; i < y.length; i++) {
            y[i] = x[i + 1] - x[i];
        }
        return y;
    }

    /**
     * Inner product for Cartesian coordinates.
     * <p>
     * MATLAB:
     * <pre>{@code c = dot(x, y);}</pre>
     *
     * @param x The first of the two input arrays.
     * @param y The second of the two input arrays.
     * @return The inner product.
     */
    public static double innerProduct(@Nonnull double[] x, @Nonnull double[] y) {
        checkEqualLength(x, y);

        double z = 0;
        for (int i = 0; i < x.length; i++) {
            z += x[i] * y[i];
        }

        return z;
    }

    /**
     * Calculates the Neper logarithm on each item of the array. Operation is <b>in place</b>.
     * <p>
     * MATLAB:
     * <pre>{@code x = log(x);}</pre>
     *
     * @param x The input array.
     */
    public static void log(@Nonnull double[] x) {
        for (int i = 0; i < x.length; i++) {
            x[i] = Math.log(x[i]);
        }
    }

    /**
     * Returns the maximum item of a part of an array.
     * <p>
     * MATLAB:
     * <pre>{@code v = max(x(i1:i2));}</pre>
     *
     * @param x  The input array.
     * @param i1 The first index of the array to take into account.
     * @param i2 The last index of the array to take into account.
     * @return The maximum item.
     */
    public static double max(@Nonnull double[] x, int i1, int i2) {
        return x[maxIdx(x, i1, i2)];
    }

    /**
     * Returns the index of the maximum item of a part of an array.
     * <p>
     * MATLAB:
     * <pre>{@code [~, idx] = v = max(x(i1:i2));}</pre>
     *
     * @param x  The array of doubles
     * @param i1 The first index of the array to take into account
     * @param i2 The last index of the array to take into account
     * @return The index of the maximum item
     */
    public static int maxIdx(@Nonnull double[] x, int i1, int i2) {
        // Assume max item is the first
        int idx = i1;

        // Loop on the remaining and replace max item index if necessary
        for (int i = i1 + 1; i <= i2; i++) {
            if (x[idx] < x[i]) {
                idx = i;
            }
        }

        return idx;
    }

    /**
     * Average of an array.
     * <p>
     * MATLAB:
     * <pre>{@code y = mean(x);}</pre>
     *
     * @param x The input array.
     * @return The average.
     */
    public static double mean(@Nonnull double[] x) {
        return sum(x) / x.length;
    }

    /**
     * Average of an array.
     * <p>
     * MATLAB:
     * <pre>{@code y = mean(x);}</pre>
     *
     * @param x The input array.
     * @return The average.
     */
    public static double mean(@Nonnull long[] x) {
        return sum(x) / x.length;
    }

    /**
     * Median of an array.
     * <p>
     * MATLAB:
     * <pre>{@code y = median(x);}</pre>
     *
     * @param x The input array.
     * @return The median.
     */
    public static double median(@Nonnull double[] x) {
        // Sort
        final double[] y = x.clone();
        Arrays.sort(y);

        // Middle item index
        int i = y.length / 2 - 1;

        if (y.length % 2 == 0) {
            return (y[i] + y[i + 1]) / 2;
        } else {
            return y[i + 1];
        }
    }

    /**
     * Returns the minimum item of a part of an array.
     * <p>
     * MATLAB:
     * <pre>{@code v = min(x(i1:i2));}</pre>
     *
     * @param x  The input array.
     * @param i1 The first index of the array to take into account.
     * @param i2 The last index of the array to take into account.
     * @return The minimum item.
     */
    public static double min(@Nonnull double[] x, int i1, int i2) {
        return x[minIdx(x, i1, i2)];
    }

    /**
     * Returns the index of the minimum item of a part of an array.
     * <p>
     * MATLAB:
     * <pre>{@code [~, idx] = min(x(i1:i2));}</pre>
     *
     * @param x  The input array.
     * @param i1 The first index of the array to take into account.
     * @param i2 The last index of the array to take into account.
     * @return The index of the minimum item.
     */
    public static int minIdx(@Nonnull double[] x, int i1, int i2) {
        // Assume max item is the first
        int idx = i1;

        // Loop on the remaining and replace min item index if necessary
        for (int i = i1 + 1; i <= i2; i++) {
            if (x[idx] > x[i]) {
                idx = i;
            }
        }

        return idx;
    }

    /**
     * Norm of level 1 of an array.
     * <p>
     * MATLAB:
     * <pre>{@code y = norm(x, 1);}</pre>
     *
     * @param x The input array.
     * @return Its level 1 norm.
     */
    public static double normL1(@Nonnull double[] x) {
        // Initialise sum
        double y = 0;

        // Loop and update sum
        for (double v : x) {
            y += Math.abs(v);
        }

        return y;
    }

    /**
     * Norm of level 2 of an array.
     * <p>
     * MATLAB:
     * <pre>{@code y = norm(x, 2);}</pre>
     *
     * @param x The input array.
     * @return Its level 2 norm.
     */
    public static double normL2(@Nonnull double[] x) {
        // Initialise sum
        double y = 0;

        // Loop and update sum
        for (double v : x) {
            y += v * v;
        }

        // Square root
        y = Math.sqrt(y);

        return y;
    }

    /**
     * Raises each element of an array to a power. Operation is <b>in place</b>.
     * <p>
     * MATLAB:
     * <pre>{@code x = x.^n;}</pre>
     *
     * @param x The input array.
     * @param n The power.
     */
    public static void powInPlace(@Nonnull double[] x, int n) {
        for (int i = 0; i < x.length; i++) {
            x[i] = Math.pow(x[i], n);
        }
    }

    /**
     * Unbiased standard deviation of an array.
     * <p>
     * MATLAB:
     * <pre>{@code y = std(x);}</pre>
     *
     * @param x The input array.
     * @return Its standard deviation.
     */
    public static double std(@Nonnull double[] x) {
        return Math.sqrt(var(x, true));
    }

    /**
     * Standard deviation of an array.
     * <p>
     * MATLAB:
     * <pre>{@code y = std(x);}</pre>
     *
     * @param x        The input array.
     * @param unbiased If true, the unbiased formula is used (sum is divided by N-1 where N is the length of x).
     * @return Its standard deviation.
     */
    public static double std(@Nonnull double[] x, boolean unbiased) {
        return Math.sqrt(var(x, unbiased));
    }

    /**
     * Sum of an array.
     * <p>
     * MATLAB:
     * <pre>{@code y = sum(x);}</pre>
     *
     * @param x The input array
     * @return Its sum
     */
    public static double sum(@Nonnull double[] x) {
        double y = 0;
        for (double v : x) {
            y += v;
        }

        return y;
    }

    /**
     * Sum of an array.
     * <p>
     * MATLAB:
     * <pre>{@code y = sum(x);}</pre>
     *
     * @param x The input array
     * @return Its sum
     */
    public static double sum(@Nonnull long[] x) {
        double y = 0;
        for (double v : x) {
            y += v;
        }

        return y;
    }

    /**
     * Sum of an array.
     * <p>
     * MATLAB:
     * <pre>{@code y = sum(x);}</pre>
     *
     * @param b The input array
     * @return Its sum
     */
    public static int sum(@Nonnull boolean[] b) {
        int s = 0;
        for (boolean bi : b) {
            if (bi) {
                s++;
            }
        }
        return s;
    }

    /**
     * Set unique. Returns an array with the same values of the input array but with no repetitions.
     * <p>
     * MATLAB:
     * <pre>{@code y = unique(x);}</pre>
     *
     * @param x The input array.
     * @return The unique array.
     */
    @Nonnull
    public static double[] unique(@Nonnull double[] x) {
        // Initialise selected items array
        boolean[] s = new boolean[x.length];

        // Set first as true
        s[0] = true;

        // Loop and detect uniqueness
        for (int i = 1; i < x.length; i++) {

            // Set x[i] as selected
            s[i] = true;

            // Loop on previous data
            for (int j = 0; j < i; j++) {

                // If the same value exists, deselect and break search
                if (x[i] == x[j]) {
                    s[i] = false;
                    break;
                }
            }
        }

        return SelectorsSetters.select(x, s);
    }

    /**
     * Set unique. Returns a boolean array indicating the unique items of the
     * input array. For items contained more than once in the array, the first
     * one is marked true (unique) and all the other false (duplicates).
     * <p>
     * MATLAB:
     * <pre>{@code [~, idx, ~] = unique(x);
     * b = false(size(x));
     * b(idx) = true;}</pre>
     *
     * @param x The input array (double).
     * @return The unique array.
     */
    @Nonnull
    public static boolean[] uniqueSelector(@Nonnull double[] x) {
        // Initialise selected items array
        boolean[] s = new boolean[x.length];

        // Set first as true
        s[0] = true;

        // Loop and detect uniqueness
        for (int i = 1; i < x.length; i++) {

            // Set x[i] as selected
            s[i] = true;

            // Loop on previous data
            for (int j = 0; j < i; j++) {

                // If the same value exists, deselect and break search
                if (x[i] == x[j]) {
                    s[i] = false;
                    break;
                }
            }
        }

        return s;
    }

    /**
     * Set unique. Returns a boolean array indicating the unique items of the
     * input array. For items contained more than once in the array, the first
     * one is marked true (unique) and all the other false (duplicates).
     * <p>
     * MATLAB:
     * <pre>{@code [~, idx, ~] = unique(x);
     * b = false(size(x));
     * b(idx) = true;}</pre>
     *
     * @param x The input array (double).
     * @return The unique array.
     */
    @Nonnull
    public static boolean[] uniqueSelector(@Nonnull int[] x) {
        // Initialise selected items array
        boolean[] s = new boolean[x.length];

        // Set first as true
        s[0] = true;

        // Loop and detect uniqueness
        for (int i = 1; i < x.length; i++) {

            // Set x[i] as selected
            s[i] = true;

            // Loop on previous data
            for (int j = 0; j < i; j++) {

                // If the same value exists, deselect and break search
                if (x[i] == x[j]) {
                    s[i] = false;
                    break;
                }
            }
        }

        return s;
    }

    /**
     * Variance of an array.
     * <p>
     * MATLAB:
     * <pre>{@code y = var(x);}</pre>
     *
     * @param x        The input array.
     * @param unbiased If true, the unbiased formula is used (sum is divided by N-1 where N is the length of x).
     * @return The variance.
     */
    public static double var(@Nonnull double[] x, boolean unbiased) {
        // Estimate mean of x
        double mu = mean(x);

        // Initialise sum
        double y = 0;

        // Loop and update sum
        for (double v : x) {
            y += Math.pow(v - mu, 2);
        }

        if (unbiased) {
            y /= x.length - 1;
        } else {
            y /= x.length;
        }

        return y;
    }
}
