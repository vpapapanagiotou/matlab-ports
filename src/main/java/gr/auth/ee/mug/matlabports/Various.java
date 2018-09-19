package gr.auth.ee.mug.matlabports;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

import javax.annotation.Nonnull;


/**
 * Various functions.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public final class Various {

    /**
     * Convert a nan or inf value to 0.
     *
     * @param x The value to check.
     * @return The fixed value.
     */
    public static double fixNanInf(double x) {
        if (Double.isNaN(x) | Double.isInfinite(x)) {
            return 0;
        } else {
            return x;
        }
    }

    /**
     * Histogram for integers.
     * <p>
     * MATLAB:
     * <pre>{@code h = hist(x, 0:n - 1);}</pre>
     *
     * @param x The observed values.
     * @param n Specifies the bins as: 0, 1, 2, ..., n - 1
     * @return The histogram values.
     */
    @Nonnull
    public static int[] hist(@Nonnull int[] x, int n) {
        final int[] y = new int[n];
        for (int i = 0; i < y.length; i++) {
            y[i] = 0;
        }

        for (int v : x) {
            y[v]++;
        }

        return y;
    }

    /**
     * For a given x, find the smallest integer b so that
     * x <= 2^b
     * and return 2^b.
     *
     * @param x The given x
     * @return 2^b
     */
    public static int nextPow2(double x) {
        int y = 1;
        while (y < x) {
            y *= 2;
        }

        return y;
    }

//    /**
//     * Toeplitz matrix form a vector.
//     * <p>
//     * MATLAB:
//     * <pre>{@code }</pre>
//     * <p>
//     * WARNING works only for real-valued vectors (no conjugates are computed)
//     */
//    public static void toeplitz(double[] x, Array2DRowRealMatrix y) {
//        // TODO Convert to double[][] perhaps?
//        for (int i = 0; i < x.length; i++) {
//            y.setEntry(i, i, x[0]);
//        }
//
//        for (int i = 1; i < x.length; i++) {
//            for (int j = 0; j < x.length - i; j++) {
//                y.setEntry(i + j, j, x[i]);
//                y.setEntry(j, i + j, x[i]);
//                // y[i+j][j] = x[i];
//                // y[j][i+j] = x[i];
//            }
//        }
//    }
}
