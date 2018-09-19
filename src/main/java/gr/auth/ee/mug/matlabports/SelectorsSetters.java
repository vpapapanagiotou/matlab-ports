package gr.auth.ee.mug.matlabports;

import javax.annotation.Nonnull;

import static gr.auth.ee.mug.matlabports.Checks.checkEqualLength;
import static gr.auth.ee.mug.matlabports.CommonFunctions.sum;
import static java.lang.Integer.signum;


/**
 * Methods to read parts of arrays and write parts of arrays.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public final class SelectorsSetters {

    public static final int END = -1;

    /**
     * Create a boolean selector. Negative steps are supported.
     * <p>
     * MATLAB:
     * <pre>{@code b = start:step:stop;}</pre>
     * Note - you can use {@code END} as MATLAB's end.
     *
     * @param start The index of the first item to be selected.
     * @param step  The step to use while selecting items.
     * @param stop  The index of the last item to be selected (inclusive).
     * @param n     The length of the array that this is selector is going to be used with.
     * @return The selected items.
     */
    @Nonnull
    public static boolean[] createSelector(int start, int step, int stop, int n) {
        if (start == END) {
            start = n - 1;
        }
        if (stop == END) {
            stop = n - 1;
        }

        final boolean[] b = new boolean[n];
        int i = start;
        int si = signum(step);
        stop = si * stop;
        while (i * si <= stop) {
            b[i] = true;
            i += step;
        }

        return b;
    }

    /**
     * Converts a boolean selector to an index selector.
     * <p>
     * MATLAB:
     * <pre>{@code idx = find(b);}</pre>
     *
     * @param b The boolean selector.
     * @return The indices of x that are true.
     */
    @Nonnull
    public static int[] find(@Nonnull boolean[] b) {
        final int n = sum(b);

        // Initialise return variable idx
        final int[] idx = new int[n];

        // Loop and select
        int i = 0;
        int p = 0;

        while (p < n) {
            if (b[i]) {
                idx[p] = i;
                p++;
            }
            i++;
        }

        return idx;
    }

    /**
     * Select from an array using a generated index. Negative steps are supported.
     * <p>
     * MATLAB:
     * <pre>{@code c = b(start:step:stop);}</pre>
     * Note - you can use {@code END} as MATLAB's end.
     *
     * @param b     The array to select from.
     * @param start The index of the first item to be selected.
     * @param step  The step to use while selecting items.
     * @param stop  The index of the last item to be selected (inclusive).
     * @return The selected items.
     */
    @Deprecated
    @Nonnull
    public static boolean[] select(@Nonnull boolean[] b, int start, int step, int stop) {
        final boolean[] selector = createSelector(start, step, stop, b.length);
        return select(b, selector);
    }

    /**
     * Select from an array using a generated index. Negative steps are supported.
     * <p>
     * MATLAB:
     * <pre>{@code y = x(start:step:stop);}</pre>
     * Note - you can use {@code END} as MATLAB's end.
     *
     * @param x     The array to select from.
     * @param start The index of the first item to be selected.
     * @param step  The step to use while selecting items.
     * @param stop  The index of the last item to be selected (inclusive).
     * @return The selected items.
     */
    @Deprecated
    @Nonnull
    public static double[] select(@Nonnull double[] x, int start, int step, int stop) {
        final boolean[] selector = createSelector(start, step, stop, x.length);
        return select(x, selector);
    }

    /**
     * Select from an array using a boolean selector.
     * <p>
     * MATLAB:
     * <pre>{@code y = x(b);}</pre>
     *
     * @param x The array to select from.
     * @param b A boolean selector.
     * @return The selected items.
     */
    @Nonnull
    public static double[] select(@Nonnull double[] x, @Nonnull boolean[] b) {
        checkEqualLength(x, b);

        final int n = sum(b);
        double[] y = new double[n];

        // Loop and select
        int i = 0;
        int p = 0;
        while (p < n) {
            if (b[i]) {
                y[p] = x[i];
                p++;
            }
            i++;
        }

        return y;
    }

    /**
     * Select from an array using a boolean selector.
     * <p>
     * MATLAB:
     * <pre>{@code y = x(b);}</pre>
     *
     * @param x The array to select from.
     * @param b A boolean selector.
     * @return The selected items.
     */
    @Nonnull
    public static int[] select(@Nonnull int[] x, @Nonnull boolean[] b) {
        checkEqualLength(x, b);

        final int n = sum(b);
        int[] y = new int[n];

        // Loop and select
        int i = 0;
        int p = 0;
        while (p < n) {
            if (b[i]) {
                y[p] = x[i];
                p++;
            }
            i++;
        }

        return y;
    }

    /**
     * Select from an array using a boolean selector.
     * <p>
     * MATLAB:
     * <pre>{@code y = x(b);}</pre>
     *
     * @param x The array to select from.
     * @param b A boolean selector.
     * @return The selected items.
     */
    @Nonnull
    public static boolean[] select(@Nonnull boolean[] x, @Nonnull boolean[] b) {
        checkEqualLength(x, b);

        final int n = sum(b);
        boolean[] y = new boolean[n];

        // Loop and select
        int i = 0;
        int p = 0;
        while (p < n) {
            if (b[i]) {
                y[p] = x[i];
                p++;
            }
            i++;
        }

        return y;
    }

    /**
     * Select from an array using an index selector.
     * <p>
     * MATLAB:
     * <pre>{@code y = x(idx);}</pre>
     *
     * @param x   The array to select from.
     * @param idx An  index selector.
     * @return The selected items.
     */
    @Nonnull
    public static boolean[] select(@Nonnull boolean[] x, @Nonnull int[] idx) {
        // Initialise return variable y
        boolean[] y = new boolean[idx.length];

        // Loop and select
        for (int i = 0; i < idx.length; i++) {
            y[i] = x[idx[i]];
        }

        return y;
    }

    /**
     * Select from an array using an index selector.
     * <p>
     * MATLAB:
     * <pre>{@code y = x(idx);}</pre>
     *
     * @param x   The array to select from.
     * @param idx An  index selector.
     * @return The selected items.
     */
    @Nonnull
    public static double[] select(@Nonnull double[] x, @Nonnull int[] idx) {
        // Initialise return variable y
        double[] y = new double[idx.length];

        // Loop and select
        for (int i = 0; i < idx.length; i++) {
            y[i] = x[idx[i]];
        }

        return y;
    }

    /**
     * Select from an array using an index selector. Out-of-bounds indices are filled with the value of padding.
     * <p>
     * MATLAB:
     * <pre>{@code y = x(idx);}</pre>
     *
     * @param x       The array to select from.
     * @param idx     An  index selector.
     * @param padding The value used for out-of-bounds indices.
     * @return The selected items.
     */
    @Nonnull
    public static boolean[] selectPadded(@Nonnull boolean[] x, @Nonnull int[] idx, boolean padding) {
        // Initialise return variable y
        boolean[] y = new boolean[idx.length];

        // Loop and select
        for (int i = 0; i < idx.length; i++) {
            if (idx[i] < 0 || x.length <= idx[i]) {
                y[i] = padding;
            } else {
                y[i] = x[idx[i]];
            }
        }

        return y;
    }

    /**
     * Select from an array using an index selector. Out-of-bounds indices are filled with the value of padding.
     * <p>
     * MATLAB:
     * <pre>{@code y = x(idx);}</pre>
     *
     * @param x       The array to select from.
     * @param idx     An  index selector.
     * @param padding The value used for out-of-bounds indices.
     * @return The selected items.
     */
    @Nonnull
    public static double[] selectPadded(@Nonnull double[] x, @Nonnull int[] idx, double padding) {
        // Initialise return variable y
        double[] y = new double[idx.length];

        // Loop and select
        for (int i = 0; i < idx.length; i++) {
            if (idx[i] < 0 || x.length <= idx[i]) {
                y[i] = padding;
            } else {
                y[i] = x[idx[i]];
            }
        }

        return y;
    }

    /**
     * Set part of an array to a single value. Operation is <b>in place</b>.
     * <p>
     * MATLAB:
     * <pre>{@code x(b) = v;}</pre>
     *
     * @param x The array to modify.
     * @param b A boolean selector.
     * @param v The value to set selected values
     */
    public static void set(@Nonnull double[] x, @Nonnull boolean[] b, double v) {
        for (int i = 0; i < x.length; i++) {
            if (b[i]) {
                x[i] = v;
            }
        }
    }

    /**
     * Set part of an array to a single value. Operation is <b>in place</b>.
     * <p>
     * MATLAB:
     * <pre>{@code x(idx) = v;}</pre>
     *
     * @param x   The array to modify.
     * @param idx An index selector.
     * @param v   The value to set selected values
     */
    public static void set(@Nonnull double[] x, @Nonnull int[] idx, double v) {
        for (int i : idx) {
            x[i] = v;
        }
    }

    /**
     * Set part of an array to a single value. Operation is <b>in place</b>.
     * <p>
     * MATLAB:
     * <pre>{@code x(idx) = v;}</pre>
     *
     * @param x   The array to modify.
     * @param idx An index selector.
     * @param v   The value to set selected values
     */
    public static void set(@Nonnull boolean[] x, @Nonnull int[] idx, boolean v) {
        for (int i : idx) {
            x[i] = v;
        }
    }

    /**
     * Set part of an array to a single value. Operation is <b>in place</b>.
     * <p>
     * MATLAB:
     * <pre>{@code x(b) = v;}</pre>
     *
     * @param x The array to modify.
     * @param b A boolean selector.
     * @param v The value to set selected values
     */
    public static void set(@Nonnull boolean[] x, @Nonnull boolean[] b, boolean v) {
        for (int i = 0; i < x.length; i++) {
            x[i] = v;
        }
    }
}
