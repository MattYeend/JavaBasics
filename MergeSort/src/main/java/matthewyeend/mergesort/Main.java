/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package matthewyeend.mergesort;

import java.util.Arrays;

/**
 *
 * @author matthewyeend
 */
public class Main {
    public static void main(String[] args) {
        int[] a = ArrayUtil.randomIntArray(20, 100);
        System.out.println(Arrays.toString(a));
        
        MergeSorter sorter = new MergeSorter(a);
        sorter.sort();
        System.out.println(Arrays.toString(a));
    }
}
