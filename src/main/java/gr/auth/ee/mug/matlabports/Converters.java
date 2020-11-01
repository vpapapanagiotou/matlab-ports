package gr.auth.ee.mug.matlabports;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;


public class Converters {


    @Nonnull
    public static double[] toPrimitive(@Nonnull final List<Double> x) {
        @Nonnull final double[] y = new double[x.size()];

        for (int i = 0; i < y.length; i++) {
            y[i] = x.get(i);
        }

        return y;
    }
}
