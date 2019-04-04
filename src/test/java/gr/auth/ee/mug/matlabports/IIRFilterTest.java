package gr.auth.ee.mug.matlabports;

import org.junit.jupiter.api.Test;

import gr.auth.ee.mug.matlabports.exceptions.BadArrayLengthException;
import gr.auth.ee.mug.matlabports.exceptions.LengthMismatchException;


public class IIRFilterTest {

    @Test
    void test1() {
        final double[] a = new double[]{1, -1};
        final double[] b = new double[]{1, 0};
        final double[] x = new double[]{1, 2, 3, 4, 5, 6, 7};
        final double[] y = new double[x.length];
        try {
            new IIRFilter(b, a).apply(x, y);
        } catch (LengthMismatchException | BadArrayLengthException e) {
            e.printStackTrace();
        }

        System.out.println(y);
    }
}
