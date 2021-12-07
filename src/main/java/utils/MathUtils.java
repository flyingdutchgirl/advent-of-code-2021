package utils;

import java.util.Arrays;

public class MathUtils {

    public static int median(final int[] arr) {
        final int len = arr.length;
        int[] ints = Arrays.copyOf(arr, len);
        Arrays.sort(ints);

        if (len == 0) {
            throw new IllegalArgumentException("The array must contain at least one element!");
        } else if (len == 1) {
            return ints[0];
        } else if (len % 2 != 0) {
            return ints[len / 2];
        } else {
            return (ints[len / 2] + ints[len / 2 - 1]) / 2;
        }

    }

    public static int round(double d) {
        int truncated = (int) d;
        return Math.abs(d - truncated) >= 0.5
                ? truncated + 1
                : truncated;
    }


}
