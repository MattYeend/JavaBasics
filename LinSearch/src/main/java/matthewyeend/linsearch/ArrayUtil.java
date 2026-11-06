/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.linsearch;

import java.security.SecureRandom;

/**
 *
 * @author matthewyeend
 */
public class ArrayUtil {
    private static final SecureRandom GENERATOR = new SecureRandom();

    /**
     * Constructs the ArrayUtil
     */
    private ArrayUtil() {}

    /**
     * Creates an array filled with random integers.
     *
     * @param length the length of the array, must not be negative
     * @param n the exclusive upper bound of each value, must be positive
     * @return an array of random integers from 0 (inclusive) to n (exclusive)
     * @throws IllegalArgumentException if length is negative or n is not positive
     */
    public static int[] randomIntArray(int length, int n) {
        if (length < 0) {
            throw new IllegalArgumentException("length must not be negative");
        }
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive");
        }

        int[] values = new int[length];
        for (int i = 0; i < values.length; i++) {
            values[i] = GENERATOR.nextInt(n);
        }
        return values;
    }
}
