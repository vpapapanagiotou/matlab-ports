package gr.auth.ee.mug.matlabports;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import gr.auth.ee.mug.matlabports.DSP;
import gr.auth.ee.mug.matlabports.exceptions.BadArrayLengthException;
import gr.auth.ee.mug.matlabports.exceptions.LengthMismatchException;
import gr.auth.ee.mug.matlabports.exceptions.UnknownTimeUnitException;


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
}
