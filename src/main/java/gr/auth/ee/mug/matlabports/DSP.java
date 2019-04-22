package gr.auth.ee.mug.matlabports;

import org.apache.commons.math3.util.MathArrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;

import gr.auth.ee.mug.matlabports.exceptions.BadArrayLengthException;
import gr.auth.ee.mug.matlabports.exceptions.LengthMismatchException;
import gr.auth.ee.mug.matlabports.exceptions.UnknownTimeUnitException;

import static gr.auth.ee.mug.matlabports.ArithmeticOperators.add;
import static gr.auth.ee.mug.matlabports.Checks.checkEqualLength;
import static gr.auth.ee.mug.matlabports.CommonFunctions.diff;
import static gr.auth.ee.mug.matlabports.CommonFunctions.innerProduct;
import static gr.auth.ee.mug.matlabports.CommonFunctions.mean;
import static gr.auth.ee.mug.matlabports.CommonFunctions.normL2;
import static gr.auth.ee.mug.matlabports.SelectorsSetters.createSelector;
import static gr.auth.ee.mug.matlabports.SelectorsSetters.select;
import static gr.auth.ee.mug.matlabports.Tools.getTimeFactor;
import static gr.auth.ee.mug.matlabports.Tools.toPrimitive;
import static java.lang.Math.max;
import static java.lang.Math.min;


/**
 * Digital signal processing functions.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public final class DSP {

    /**
     * Sample auto-correlation for lags 0, 1, ..., m.
     * <p>
     * MATLAB:
     * <pre>{@code r = autocorr(x, m);}</pre>
     *
     * @param x The input array.
     * @param m The max lag.
     * @return The auto-correlation values.
     */
    @Nonnull
    public static double[] autocorr(@Nonnull double[] x, int m) {
        final double[] y = new double[m + 1];

        final double mu = mean(x);
        for (int l = 0; l <= m; l++) {
            y[l] = 0;
            for (int i = 0; i < x.length - l; i++) {
                y[l] += (x[i] - mu) * (x[i + l] - mu);
            }
        }

        final double y0 = y[0];
        for (int i = 0; i < y.length; i++) {
            y[i] /= y0;
        }

        return y;
    }

    /**
     * Pearson's correlation coefficient.
     * <p>
     * MATLAB:
     * <pre>{@code r = corr(x(:), y(:));}</pre>
     *
     * @param x The first input array
     * @param y The second input array
     * @return The coefficient value
     */
    public static double corr(@Nonnull double[] x, @Nonnull double[] y) throws LengthMismatchException {
        checkEqualLength(x, y);

        final double[] x0 = add(x, -mean(x));
        final double[] y0 = add(y, -mean(y));
        final double r = innerProduct(x0, y0) / (normL2(x0) * normL2(y0));

        return r;
    }

    /**
     * Calculates the Delta Coefficients of an array.
     *
     * @param x The input array
     * @param D The parameter that defines the estimation window (equal to 2*D+1)
     * @return The Delta Coefficients of x
     */
    @Nonnull
    public static double[] deltaCoeffs(@Nonnull double[] x, int D) throws LengthMismatchException {
        // TODO This is not checked yet.
        if (x.length == 0) {
            throw new LengthMismatchException();
        }

        // Length of input array
        final int n = x.length;

        // Allocate buffer y and output z
        final double[] y = new double[n + 2 * D];
        final double[] z = new double[n];

        // Fill in buffer y
        for (int i = 0; i < D; i++) {
            y[i] = x[0];
            y[y.length - 1 - i] = x[n - 1];
        }
        System.arraycopy(x, 0, y, D, n);

        // Calculate result
        for (int i = 0; i < n; i++) {
            z[i] = 0;
            for (int j = -D; j <= D; j++) {
                z[i] += j * y[i + D + j];
            }
        }

        // Normalisation parameter
        double a = 0;
        for (int i = 1; i <= D; i++) {
            a += i * i;
        }
        a *= 2;

        // Normalise
        for (int i = 0; i < n; i++) {
            z[i] /= a;
        }

        return z;
    }

    /**
     * One-dimensional dilation of an array on pre-allocated output
     *
     * @param x     The input array
     * @param y     The output array
     * @param m     Parameter that defines the length of the structure element
     * @param shift If true, output array is shifted by -1
     */
    public static void dil1d(@Nonnull double[] x, @Nonnull double[] y, int m, boolean shift)
            throws LengthMismatchException {
        // TODO This is not checked yet.
        if (x.length != y.length) {
            throw new LengthMismatchException();
        }

        // Define left and write offsets
        int nl;
        int nr;
        if (m % 2 == 1) {
            nl = (m - 1) / 2;
            nr = (m - 1) / 2;
        } else {
            nl = m / 2;
            nr = m / 2 - 1;
        }

        // Adjust shifting
        if (shift) {
            nl--;
            nr++;
        }

        // Dilate the fist N2 elements
        for (int i = 0; i < m; i++) {
            y[i] = CommonFunctions.max(x, 0, i + nr);
        }

        // Dilate the middle elements
        for (int i = m; i < x.length - m; i++) {
            y[i] = CommonFunctions.max(x, i - nl, i + nr);
        }

        // Dilate the last N2 elements
        for (int i = x.length - m; i < x.length; i++) {
            y[i] = CommonFunctions.max(x, i - nl, x.length - 1);
        }
    }

    /**
     * One-dimensional dilation of an array
     *
     * @param x     The input array
     * @param m     Parameter that defines the length of the structure element
     * @param shift If true, output array is shifted by -1 (default is false)
     * @return The dilated array
     */
    @Nonnull
    public static double[] dil1d(@Nonnull double[] x, int m, boolean shift) throws LengthMismatchException {
        // TODO This is not checked yet.
        // Allocate output vector
        double[] y = new double[x.length];
        // Perform dilation
        dil1d(x, y, m, shift);
        return y;
    }

    /**
     * One-dimensional dilation of an array (shift=false wrapper for compatibility)
     *
     * @param x The input array
     * @param m Parameter that defines the length of the structure element
     * @return The dilated array
     */
    @Nonnull
    public static double[] dil1d(@Nonnull double[] x, int m) throws LengthMismatchException {
        // TODO This is not checked yet.
        return dil1d(x, m, false);
    }

    /**
     * One-dimensional erosion of an array on pre-allocated output
     *
     * @param x     The input array
     * @param y     The output array
     * @param m     Parameter that defines the length of the stucturing element
     * @param shift If true, output array is shifted by -1
     */
    public static void ero1d(@Nonnull double[] x, @Nonnull double[] y, int m, boolean shift)
            throws LengthMismatchException {
        // TODO This is not checked yet.
        if (x.length != y.length) {
            throw new LengthMismatchException();
        }

        // Define left and write offsets
        int nl;
        int nr;
        if (m % 2 == 1) {
            nl = (m - 1) / 2;
            nr = (m - 1) / 2;
        } else {
            nl = m / 2 - 1;
            nr = m / 2;
        }

        // Adjust shifting
        if (shift) {
            nl--;
            nr++;
        }

        // Erode the first n elements
        for (int i = 0; i < m; i++) {
            y[i] = CommonFunctions.min(x, 0, i + nr);
        }

        // Erode the middle elements
        for (int i = m; i < x.length - m; i++) {
            y[i] = CommonFunctions.min(x, i - nl, i + nr);
        }

        // Erode the last N2 elements
        for (int i = x.length - m; i < x.length; i++) {
            y[i] = CommonFunctions.min(x, i - nl, x.length - 1);
        }
    }

    /**
     * One-dimensional erosion of an array
     *
     * @param x     The input array
     * @param m     Parameter that defines the length of the structure element
     * @param shift If true, output array is shifted by -1 (default is false)
     * @return The eroded array
     */
    @Nonnull
    public static double[] ero1d(@Nonnull double[] x, int m, boolean shift) throws LengthMismatchException {
        // TODO This is not checked yet.
        // Allocate output vector
        double[] y = new double[x.length];
        // Perform dilation
        ero1d(x, y, m, shift);
        return y;
    }

    /**
     * One-dimensional erosion of an array (shift=false wrapper for compatibility)
     *
     * @param x The input array
     * @param m Parameter that defines the length of the structure element
     * @return The eroded array
     */
    @Nonnull
    public static double[] ero1d(@Nonnull double[] x, int m) throws LengthMismatchException {
        // TODO This is not checked yet.
        return ero1d(x, m, false);
    }

    /**
     * Estimates the sampling frequency (in Hz) from an array of timestamps.
     *
     * @param t The timestamps (in sec)
     * @return The estimated sampling frequency
     */
    public static double estimateFs(@Nonnull double[] t)
            throws LengthMismatchException, BadArrayLengthException, UnknownTimeUnitException {
        return estimateFs(t, TimeUnit.SECONDS);
    }

    /**
     * Estimates the sampling frequency (in Hz) from an array of timestamps.
     *
     * @param t        The timestamps
     * @param timeUnit The timestamps time unit
     * @return The estimated sampling frequency
     */
    public static double estimateFs(@Nonnull double[] t, @Nonnull TimeUnit timeUnit)
            throws BadArrayLengthException, LengthMismatchException, UnknownTimeUnitException {

        if (t.length < 2) {
            throw new BadArrayLengthException("Too short array t; is " + t.length + " (should be >= 2)");
        }

        final double[] dt = diff(t);
        Arrays.sort(dt);
        final int n = dt.length;
        final int i1 = Math.max(0, (int) Math.round(.1 * n) - 1);
        final int i2 = min(n - 1, (int) Math.round(.9 * n) - 1);

        final boolean[] b = createSelector(i1, 1, i2, dt.length);
        final double[] sdt = select(dt, b);
        return 1 / getTimeFactor(timeUnit) / mean(sdt);
    }

    /**
     * Frequencies of FFT
     *
     * @param n  The number of samples of the FFT
     * @param fs The sampling frequencies
     * @return An array with the frequencies
     */
    @Nonnull
    public static double[] fftf(int n, double fs) {
        // TODO This is not checked yet.
        final double[] f = new double[n];
        final int a = n / 2 + 1;
        final int b = n - a;
        final double df = fs / 2 / (a - 1);
        for (int i = 0; i < a; i++) {
            f[i] = i;
        }
        for (int i = 1; i < b + 1; i++) {
            f[n - i] = i;
        }
        for (int i = 0; i < n; i++) {
            f[i] = f[i] * df;
        }
        return f;
    }

    /**
     * Find signal peaks.
     * <p>
     * MATLAB:
     * <pre>{@code [~, i] = findpeaks(x, 'MinPeakDistance', minPeakDistance, 'MinPeakProminence', minPeakProminence);}</pre>
     *
     * @param x                 The input signal.
     * @param minPeakDistance   Minimum distance between peaks.
     * @param minPeakProminence Minimum prominence of each peak.
     * @return The indices corresponding to detected peaks (indices and values).
     */
    public static int[] findPeaks(@Nonnull double[] x, int minPeakDistance, double minPeakProminence) {
        if (x.length < 3) {
            return new int[0]; // no peaks
        }

        /* Find peaks */
        @Nonnull ArrayList<Integer> idx = findPeaksBase(x);
        if (idx.isEmpty()) {
            return new int[0];
        }

        if (minPeakProminence > 0) {
            idx = findPeaksPeakProminence2(x, idx, minPeakProminence);
        }
        if (minPeakDistance > 0) {
            idx = findPeaksPeakDistance(x, idx, minPeakDistance);
        }

        final int[] r = toPrimitive(idx, -1);
        Arrays.sort(r);
        return r;
    }

    /**
     * One-dimension gradient.
     * <p>
     * MATLAB:
     * <pre>{@code y = gradient(x);}</pre>
     *
     * @param x The input array.
     * @return The gradient array.
     */
    public static double[] gradient1d(@Nonnull double[] x) {
        // If input is empty, abandon
        if (x.length == 0) {
            return new double[0];
        }

        // Array length
        final int n = x.length;
        final double[] y = new double[n];

        // Escape if trivial case of length equal to 1
        if (n == 1) {
            y[0] = 0;
            return y;
        }

        // Do the computation of 1D-gradient
        y[0] = x[1] - x[0];
        for (int i = 1; i < n - 1; i++) {
            y[i] = (x[i + 1] - x[i - 1]) / 2;
        }
        y[n - 1] = x[n - 1] - x[n - 2];

        return y;
    }

    /**
     * Computes a hamming window.
     * <p>
     * MATLAB:
     * <pre>{@code x = hamming(n);}</pre>
     *
     * @param n Window length.
     * @return The window.
     */
    public static double[] hamming(int n) {
        final double[] x = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = 0.54 - 0.46 * Math.cos(2 * Math.PI * i / (n - 1));
        }

        return x;
    }

    /**
     * One-dimensional image-opening of an array.
     *
     * @param x The input array
     * @param n Parameter that defines the length of the stucture element
     * @return The image-opened array
     */
    public static double[] imopen(double[] x, int n) throws LengthMismatchException {
        // TODO This is not checked yet.
        // Check if x is null
        if (x == null) {
            return null;
        }

        int m = x.length;

        // Padding
        double[] y = new double[m + 2 * n];
        for (int i = 0; i < n; i++) {
            y[i] = x[0];
            y[y.length - 1 - i] = x[m - 1];
        }
        System.arraycopy(x, 0, y, n, m);

        // Perform image opening
        y = dil1d(ero1d(y, n), n);

        // Initialise return buffer
        double[] z = new double[m];

        // Populate buffer
        System.arraycopy(y, n, z, 0, m);

        return z;
    }

    private static ArrayList<Integer> findPeaksBase(@Nonnull double[] x) {
        @Nonnull final ArrayList<Integer> idx = new ArrayList<>();

        int i = 1;
        while (i < x.length - 1) {
            if (x[i] <= x[i - 1]) {
                i++;
                continue;
            }

            int j;
            for (j = i + 1; j < x.length; j++) {
                if (x[j] != x[i]) {
                    if (x[i] > x[j]) {
                        idx.add(i);
                    }
                    break;
                }
            }

            i = j;
        }

        return idx;
    }

    private static ArrayList<Integer> findPeaksPeakDistance(
            @Nonnull final double[] x, @Nonnull List<Integer> idx, final double minPeakDistance) {

        // Create index and value arrays for input peaks
        final int n = idx.size();
        final double[] idxI = new double[n];
        final double[] idxV = new double[n];
        for (int i = 0; i < n; i++) {
            idxI[i] = idx.get(i);
            idxV[i] = x[(int) idxI[i]];
        }

        // Sort these arrays
        MathArrays.sortInPlace(idxV, MathArrays.OrderDirection.DECREASING, idxI);

        /* Enforce minimum peak distance */
        final ArrayList<Integer> idx2 = new ArrayList<Integer>();
        idx2.add((int) idxI[0]);
        for (int i = 1; i < n; i++) {
            // Find minimum distance
            double minDist = Math.abs(idxI[i] - idx2.get(0));
            for (int j = 1; j < idx2.size(); j++) {
                minDist = min(minDist, Math.abs(idxI[i] - idx2.get(j)));
            }
            // Add index if minDist is small enough
            if (minDist > minPeakDistance) {
                idx2.add((int) idxI[i]);
            }
        }

        return idx2;
    }

    private static ArrayList<Integer> findPeaksPeakProminence(
            @Nonnull final double[] x,
            @Nonnull final ArrayList<Integer> idx,
            final double minPeakProminence) {

        /* Calculate peak prominence */
        final double[] peakProminence = new double[idx.size()];

        for (int i = 0; i < peakProminence.length; i++) {

            // Peak index
            final int pIdx = idx.get(i);

            // Find left segment minimum
            int i1 = pIdx - 1;
            double leftMin = x[i1];
            while (x[i1] < x[pIdx] && i1 > 0) {
                i1--;
                if (x[i1] < leftMin) {
                    leftMin = x[i1];
                }
            }

            // Find right segment
            int i2 = pIdx + 1;
            double rightMin = x[i2];
            while (x[i2] < x[pIdx] && i2 < x.length - 1) {
                i2++;
                if (x[i2] < rightMin) {
                    rightMin = x[i2];
                }
            }

            // Compute prominence
            peakProminence[i] = x[pIdx] - max(leftMin, rightMin);
        }

        // Enforce minimum peak prominence
        final ArrayList<Integer> idx2 = new ArrayList<Integer>();

        for (int i = 0; i < idx.size(); i++) {
            if (peakProminence[i] > minPeakProminence) {
                idx2.add(idx.get(i));
            }
        }

        return idx2;
    }

    private static ArrayList<Integer> findPeaksPeakProminence2(
            @Nonnull final double[] x,
            @Nonnull final ArrayList<Integer> idx,
            final double minPeakProminence) {

        // Calculate peak prominence values
        final double[] proms = new double[idx.size()];

        for (int i = 0; i < proms.length; i++) {

            // Current peak
            final int curI = idx.get(i);
            final double curV = x[curI];

            // Left and right peaks/edges
            int leftEdgeI = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (curV < x[idx.get(j)]) {
                    leftEdgeI = idx.get(j);
                    break;
                }
            }

            int rightEdgeI = x.length - 1;
            for (int j = i + 1; j < idx.size(); j++) {
                if (curV < x[idx.get(j)]) {
                    rightEdgeI = idx.get(j);
                    break;
                }
            }

            // Find left and right minima
            double leftMinV = x[leftEdgeI];
            for (int j = leftEdgeI + 1; j < curI; j++) {
                if (x[j] < leftMinV) {
                    leftMinV = x[j];
                }
            }

            double rightMinV = x[curI];
            for (int j = curI + 1; j <= rightEdgeI; j++) {
                if (x[j] < rightMinV) {
                    rightMinV = x[j];
                }
            }

            // Compute prominence
            proms[i] = curV - Math.max(leftMinV, rightMinV);
        }

        // Enforce minimum peak prominence
        final ArrayList<Integer> idx2 = new ArrayList<Integer>();

        for (int i = 0; i < idx.size(); i++) {
            if (proms[i] > minPeakProminence) {
                idx2.add(idx.get(i));
            }
        }

        return idx2;
    }
}
