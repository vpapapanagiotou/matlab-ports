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
     *
     * @param x The input matrix
     * @param n The index to start copying from
     * @param l The length to copy
     * @return The selected sub-matrix
     */
    public static double[][] getRange(double[][] x, int n, int l) {

        double[][] y = new double[l][];

        for (int i = 0; i < l; i++) {
            y[i] = x[n + i].clone();
        }

        return y;
    }

    /**
     * Returns a sub matrix, with the same number of arrays. However, items from n up to
     * (and without) n+l are returned in each array.
     *
     * @param x The input matrix
     * @param n The index to start copying from
     * @param l The index until which to copy (non-inclusive)
     * @return The selected sub-matrix
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
