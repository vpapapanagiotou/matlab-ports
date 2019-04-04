package gr.auth.ee.mug.matlabports;

import org.junit.jupiter.api.Test;

import static gr.auth.ee.mug.matlabports.SelectorsSetters.createSelector;
import static gr.auth.ee.mug.matlabports.SelectorsSetters.find;


public class IIRFilterTest {

    @Test
    void test1() {
        final double[] a = new double[]{1, -1};
        final double[] b = new double[]{1, 0};
        final double[] x = new double[]{1, 2, 3, 4, 5, 6, 7};
        final double[] y = new double[x.length];
        new IIRFilter(b, a).apply(x,y);

        System.out.println(y);
    }
}
