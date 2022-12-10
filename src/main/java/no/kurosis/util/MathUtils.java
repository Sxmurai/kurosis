package no.kurosis.util;

/**
 * A utility class to calculate math related things or shortcuts to math
 *
 * @author aesthetical
 * @since 12/10/22
 */
public class MathUtils {

    /**
     * Credits to <a href="https://www.baeldung.com/java-calculate-standard-deviation">...</a> because fuck statistics (i also cant do math)
     * Calculates standard deviation in a set of numbers
     *
     * @param nums an array of doubles for calculations
     * @return the standard deviation
     */
    public static double standardDeviation(double[] nums) {
        double mean = average(nums);

        double standardDeviation = 0.0;
        for (double num : nums) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.sqrt(standardDeviation / nums.length);
    }

    /**
     * Calculates the average/mean by adding all the numbers and dividing by the total
     * amount of numbers in the set
     * @param nums an array of doubles to caclulate the average
     * @return the average of all the numbers
     */
    public static double average(double[] nums) {
        double s = 0.0;
        int valid = 0;
        for (double x : nums) {
            if (x > 0.0) {
                s += x;
                ++valid;
            }
        }
        return valid > 0 ? s / valid : s;
    }
}
