package gr.auth.ee.mug.matlabports;



import java.util.List;

import javax.annotation.Nullable;


/**
 * Tools for easier manipulations of primitive arrays.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public final class Tools {

    public static int[] toPrimitive(@Nullable List<Integer> arr, int nullValue) {
        if (arr == null) {
            return new int[0];
        }

        final int[] pArr = new int[arr.size()];

        for (int i = 0; i < pArr.length; i++) {
            final Integer a = arr.get(i);
            pArr[i] = a == null ? nullValue : a;
        }

        return pArr;
    }

    public static double[] toPrimitive(@Nullable List<Double> arr, double nullValue) {
        if (arr == null) {
            return new double[0];
        }

        final double[] pArr = new double[arr.size()];

        for (int i = 0; i < pArr.length; i++) {
            final Double a = arr.get(i);
            pArr[i] = a == null ? nullValue : a;
        }

        return pArr;
    }
}
