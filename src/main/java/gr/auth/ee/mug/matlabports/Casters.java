package gr.auth.ee.mug.matlabports;

import javax.annotation.Nonnull;


/**
 * Type-casting methods.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public final class Casters {

    @Nonnull
    public static double[] toDouble(@Nonnull float[] x) {
        final double[] y = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            y[i] = x[i];
        }

        return y;
    }

    @Nonnull
    public static double[] toDouble(@Nonnull int[] x) {
        final double[] y = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            y[i] = x[i];
        }

        return y;
    }

    @Nonnull
    public static int[] toInteger(@Nonnull double[] x) {
        final int[] y = new int[x.length];
        for (int i = 0; i < x.length; i++) {
            y[i] = (int) x[i];
        }

        return y;
    }
}
