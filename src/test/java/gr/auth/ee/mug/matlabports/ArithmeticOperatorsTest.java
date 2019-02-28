package gr.auth.ee.mug.matlabports;

public class ArithmeticOperatorsTest {

    @org.junit.jupiter.api.Test
    void multiply1() {

        double[] x = new double[]{1, 2, 3, 4, 4};
        double v = 1.1;
        ArithmeticOperators.multiplyInPlace(x, v);

        System.out.println(x);
    }
}
