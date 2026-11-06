/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package matthewyeend.binsearch;

import java.util.Arrays;

/**
 *
 * @author matthewyeend
 */
public class Main {
    public static void main(String[] args) {
        int[] a = ArrayUtil.randomIntArray(20, 100);
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));

        BinarySearcher searcher = new BinarySearcher(a);

        int present = a[a.length / 2];
        System.out.println("Searching for " + present + ": index "
                + searcher.search(present));

        int absent = -1;
        System.out.println("Searching for " + absent + ": index "
                + searcher.search(absent));
    }
}
