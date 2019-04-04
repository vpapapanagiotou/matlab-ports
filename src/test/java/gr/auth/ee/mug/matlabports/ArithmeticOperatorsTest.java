package gr.auth.ee.mug.matlabports;

import gr.auth.ee.mug.matlabports.exceptions.LengthMismatchException;


public class ArithmeticOperatorsTest {

    @org.junit.jupiter.api.Test
    void multiply1() {

        double[] x = new double[]{1, 2, 3, 4, 4};
        double v = 1.1;
        try {
            ArithmeticOperators.multiplyInPlace(x, v);
        } catch (LengthMismatchException e) {
            e.printStackTrace();
        }

        System.out.println(x);
    }
}
