package gr.auth.ee.mug.matlabports;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import gr.auth.ee.mug.matlabports.exceptions.UnknownTimeUnitException;


/**
 * Tools for easier manipulations of primitive arrays.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public final class Tools {

    public static double getTimeFactor(@Nonnull TimeUnit timeUnit) throws UnknownTimeUnitException {
        switch (timeUnit) {
            case NANOSECONDS:
                return 1e-9;

            case MICROSECONDS:
                return 1e-6;

            case MILLISECONDS:
                return 1e-3;

            case SECONDS:
                return 1;

            case MINUTES:
                return 60;

            case HOURS:
                return 3600;

            case DAYS:
                return 24 * 3600;

            default:
                throw new UnknownTimeUnitException();
        }
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
}
