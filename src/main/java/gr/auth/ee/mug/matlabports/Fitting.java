package gr.auth.ee.mug.matlabports;

import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;


/**
 * Fitting-related functions.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public final class Fitting {

    /**
     * Fits a quadratic curve by solving the following minimisation problem:
     * <p>
     * min J w.r.t. a, b
     * <p>
     * where J = sum (y[i] - a * t[i]^2 - b * t[i])^2, over i
     *
     * @param x Array x of minimisation problem.
     * @param y Array y of minimisation problem.
     * @return Array z, where z[0] = a and z[1] = b.
     */
    public static double[] fitQuadraticModel(double[] x, double[] y) {

        if (x.length != y.length) {
            return null;
        }

        int n = x.length;

        double[][] X = new double[n][2];
        for (int i = 0; i < n; i++) {
            X[i][0] = x[i] * x[i];
            X[i][1] = x[i];
        }

        // Instantiate library
        OLSMultipleLinearRegression r = new OLSMultipleLinearRegression();

        // Set no intercept
        // IMPORTANT no intercept should be set before adding any data
        r.setNoIntercept(true);

        // Add data
        r.newSampleData(y, X);

        // Solve the problem
        double[] params;
        try {
            params = r.estimateRegressionParameters();
        } catch (Exception e) {
            params = new double[2];
            params[0] = 0;
            params[0] = 0;
        }
        return params;
    }
}
