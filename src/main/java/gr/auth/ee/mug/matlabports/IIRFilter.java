package gr.auth.ee.mug.matlabports;

import javax.annotation.Nonnull;

import static gr.auth.ee.mug.matlabports.SelectorsSetters.set;
import static java.lang.Math.max;


public class IIRFilter {

    @Nonnull
    private final double[] a;
    @Nonnull
    private final double[] b;
    private final int bufLength;
    private final int coeffLength;
    @Nonnull
    private final double[] x;
    @Nonnull
    private final double[] y;

    public IIRFilter(@Nonnull double[] b, @Nonnull double[] a) {
        coeffLength = max(b.length, a.length);
        bufLength = coeffLength - 1;

        this.b = new double[coeffLength];
        this.a = new double[coeffLength];

        System.arraycopy(b, 0, this.b, 0, b.length);
        System.arraycopy(a, 0, this.a, 0, a.length);
        set(b, 0);
        set(a, 0);
        x = new double[coeffLength - 1];
        y = new double[coeffLength - 1];

        reset();
    }

    public void reset() {
        for (int i = 0; i < coeffLength - 1; i++) {
            x[i] = 0;
            y[i] = 0;
        }
    }

    public void apply(@Nonnull double[] x, @Nonnull double[] y) {
        int i;
        int j;

        for (i = 0; i < this.x.length; i++) {
            y[i] = b[0] * x[i];

            for (j = 1; j < i + 1; j++) {
                y[i] += b[j] * x[i - j] - a[j] * y[i - j];
            }

            for (j = i + 1; j < coeffLength; j++) {
                y[i] += b[j] * this.x[this.x.length - j + i] - a[j] * this.y[this.y.length - j + i];
            }
        }

        for (i = this.x.length; i < x.length; i++) {
            y[i] = b[0] * x[i];

            for (j = 1; j < coeffLength; j++) {
                y[i] += b[j] * x[i - j] - a[j] * y[i - j];
            }
        }

        if (this.x.length <= x.length) {
            for (j = 0; j < this.x.length; j++) {
                this.x[j] = x[x.length - this.x.length + j];
                this.y[j] = y[x.length - this.x.length + j];
            }
        } else {
            final int d = coeffLength - x.length + 1;
            for (j = 0; j < d; j++) {
                this.x[j] = x[j + x.length];
                this.y[j] = this.y[j + x.length];
            }
            for (j = d; j < this.x.length; j++) {
                this.x[j] = x[j - d];
                this.y[j] = y[j - d];
            }
        }
    }
}
