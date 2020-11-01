package gr.auth.ee.mug.matlabports;

import gr.auth.ee.mug.matlabports.exceptions.BadArrayLengthException;
import gr.auth.ee.mug.matlabports.exceptions.LengthMismatchException;

import javax.annotation.Nonnull;

import static gr.auth.ee.mug.matlabports.Checks.checkEqualLength;
import static gr.auth.ee.mug.matlabports.SelectorsSetters.set;
import static java.lang.Math.max;


public class IIRFilter {

    @Nonnull
    private final double[] a;
    @Nonnull
    private final double[] b;
    private final int coeffLength;
    @Nonnull
    private final double[] xPast;
    @Nonnull
    private final double[] yPast;

    public IIRFilter(@Nonnull double[] b, @Nonnull double[] a) throws BadArrayLengthException {
        if (b.length < 1) {
            throw new BadArrayLengthException("Length of b=" + b.length);
        }
        if (a.length < 1) {
            throw new BadArrayLengthException("Length of b=" + a.length);
        }

        coeffLength = max(b.length, a.length);

        this.b = new double[coeffLength];
        set(this.b, 0);
        System.arraycopy(b, 0, this.b, 0, b.length);

        this.a = new double[coeffLength];
        set(this.a, 0);
        System.arraycopy(a, 0, this.a, 0, a.length);

        xPast = new double[coeffLength - 1];
        yPast = new double[coeffLength - 1];

        reset();
    }

    public void reset() {
        set(xPast, 0);
        set(yPast, 0);
    }

    public void apply(@Nonnull double[] x, @Nonnull double[] y) throws LengthMismatchException, BadArrayLengthException {
        checkEqualLength(x, y);

        if (x.length < xPast.length) {
            throw new BadArrayLengthException("Refusing to work for input shorter than the filter length");
        }

        int i;  // Index of output that we are computing
        int j;  // Lag index

        // For these values we need the past values
        for (i = 0; i < xPast.length; i++) {

            // Initialize output with latest input
            y[i] = b[0] * x[i];

            // Update with as many input and output we can from the main buffers
            for (j = 1; j < i + 1; j++) {
                y[i] += b[j] * x[i - j] - a[j] * y[i - j];
            }

            // Update with what is required from the past buffers
            for (j = i + 1; j < coeffLength; j++) {
                y[i] += b[j] * xPast[xPast.length - j + i] - a[j] * yPast[yPast.length - j + i];
            }
        }

        // For the next values run normally
        for (i = xPast.length; i < x.length; i++) {

            // Initialize output with latest input
            y[i] = b[0] * x[i];

            // Update
            for (j = 1; j < coeffLength; j++) {
                y[i] += b[j] * x[i - j] - a[j] * y[i - j];
            }
        }

        // Update past
        if (xPast.length <= x.length) {
            for (j = 0; j < xPast.length; j++) {
                xPast[j] = x[x.length - xPast.length + j];
                yPast[j] = y[x.length - xPast.length + j];
            }
        } else {
            final int d = coeffLength - x.length + 1;
            for (j = 0; j < d; j++) {
                xPast[j] = xPast[j + x.length];
                yPast[j] = yPast[j + y.length];
            }
            for (j = d; j < xPast.length; j++) {
                xPast[j] = x[j - d];
                yPast[j] = y[j - d];
            }
        }
    }
}
