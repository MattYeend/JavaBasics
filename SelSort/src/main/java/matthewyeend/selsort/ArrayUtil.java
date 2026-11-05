/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.selsort;

import java.util.Random;

/**
 *
 * @author matthewyeend
 */
public class ArrayUtil {
    private static final Random generator = new Random();
    
    /**
     * Creates an array filled with random values.
     * @param length the length of the array
     * @param n the number of possible random values
     * @return an array filled with length numbers between 0 and n - 1
     */
    public static int[] randomIntArray(int length, int n) {
        int[] a = new int[length];
        for(int i = 0; i < a.length; i++)
            a[i] = generator.nextInt(n);
        return a;
    }
}
