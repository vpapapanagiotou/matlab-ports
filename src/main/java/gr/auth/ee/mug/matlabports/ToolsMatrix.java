package gr.auth.ee.mug.matlabports;

/**
 * Class that handles operations on 2-Dimensional Arrays (matrices)
 *
 * @author Vasileios Papapanagiotou [vassilis@mug.ee.auth.gr]
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class ToolsMatrix {

    /**
     * Returns a sub matrix, with l arrays starting from the n-th.
     */
    public static double[][] getRange(double[][] x, int n, int l) {

        double[][] y = new double[l][];

        for (int i = 0; i < l; i++) {
            y[i] = x[n + i].clone();
        }

        return y;
    }

    /**
     * Returns a sub matrix, with the same number of arrays. However, items from n up to (and without) n+l are returned in each array.
     */
    public static double[][] getRange2(double[][] x, int n, int l) {

        double[][] y = new double[x.length][];

        for (int i = 0; i < x.length; i++) {
            System.arraycopy(x[i], n, y[i], 0, l);
        }

        return y;
    }

    public static double[] sum(double[][] x) {

        double[] y = new double[x.length];

        for (int i = 0; i < x.length; i++) {
            y[i] = CommonFunctions.sum(x[i]);
        }

        return y;
    }
}
