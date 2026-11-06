/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.binsearch;

import java.security.SecureRandom;

/**
 *
 * @author matthewyeend
 */
public class ArrayUtil {
    private static final SecureRandom GENERATOR = new SecureRandom();
    private static final int MAX_LENGTH = 1_000_000;

    /**
     * Constructs the ArrayUtil
     */
    private ArrayUtil() {}

    /**
     * Creates an array filled with random integers.
     *
     * @param length the length of the array, between 0 and 1,000,000 inclusive
     * @param n the exclusive upper bound of each value, must be positive
     * @return an array of random integers from 0 (inclusive) to n (exclusive)
     * @throws IllegalArgumentException if length is out of range or n is not positive
     */
    public static int[] randomIntArray(int length, int n) {
        if (length < 0 || length > MAX_LENGTH) {
            throw new IllegalArgumentException(
                    "length must be between 0 and " + MAX_LENGTH);
        }
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive");
        }
        return GENERATOR.ints(length, 0, n).toArray();
    }
}
