package gr.auth.ee.mug.matlabports;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;

import gr.auth.ee.mug.matlabports.exceptions.BadArrayLengthException;
import gr.auth.ee.mug.matlabports.exceptions.LengthMismatchException;
import gr.auth.ee.mug.matlabports.exceptions.UnknownTimeUnitException;

import static gr.auth.ee.mug.matlabports.DSP.findPeaks;


public class DSPTest {

    @Test
    void estimateFs1() throws UnknownTimeUnitException, LengthMismatchException, BadArrayLengthException {
        final double[] t = new double[]{1, 2, 3, 4, 5, 6, 7};
        assert 1 == gr.auth.ee.mug.matlabports.DSP.estimateFs(t);
    }

    @Test
    void estimateFs2() {
        final double[] t = new double[]{1, 2, 3, 4, 5, 6, 7};

        double efs;
        try {
            efs = gr.auth.ee.mug.matlabports.DSP.estimateFs(t, TimeUnit.MILLISECONDS);
        } catch (UnknownTimeUnitException | BadArrayLengthException | LengthMismatchException e) {
            efs = -1;
        }

        assert Math.abs(efs - 1000) < 1e-9;
    }

    @Test
    void estimateFs3() {
        final int n = 4;

        final double[] t = new double[n];
        for (int i = 0; i < t.length; i++) {
            t[i] = i * 1000;
        }

        double efs;
        try {
            efs = DSP.estimateFs(t, TimeUnit.MILLISECONDS);
        } catch (UnknownTimeUnitException | BadArrayLengthException | LengthMismatchException e) {
            efs = -1;
        }

        assert Math.abs(efs - 1) < 1e-9;
    }

    @Test
    void findPeaks1() {
        final double[] x = new double[]{
                0.53767,
                1.8339,
                -2.2588,
                0.86217,
                0.31877,
                -1.3077,
                -0.43359,
                0.34262,
                3.5784,
                2.7694,
                -1.3499,
                3.0349,
                0.7254,
                -0.063055,
                0.71474,
                -0.20497,
                -0.12414,
                1.4897,
                1.409,
                1.4172};
        final int[] idx1 = new int[]{3, 8};
        final int[] idx2 = findPeaks(x, 3, 2);
        for (int i = 0; i < idx2.length; i++) {
            System.out.println(idx2[i]);
        }
        assert Arrays.equals(idx1, idx2);
    }

    @Test
    void medfilt1() {
        double[] x = new double[]{1, 2, 3, 4, 5, 6};
        double[] y = DSP.medfilt1(x, 4);

        printArray(y);
    }

    private void printArray(@Nonnull final double[] x) {
        for (int i = 0; i < x.length; i++) {
            System.out.println(i + ": " + x[i]);
        }
    }
}
