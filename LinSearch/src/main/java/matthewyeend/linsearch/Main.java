/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package matthewyeend.linsearch;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author matthewyeend
 */
public class Main {
    public static void main(String[] args) {
        int[] a = ArrayUtil.randomIntArray(20, 100);
        System.out.println(Arrays.toString(a));
        LinearSearch search = new LinearSearch(a);
        
        Scanner in = new Scanner(System.in);
        
        boolean done = false;
        while (!done) {
            System.out.println("Enter a number to search for, -1 to quit: ");
            int n = in.nextInt();
            if (n == -1)
                done = true;
            else {
                int pos = search.search(n);
                System.out.println("Found in position " + pos);
            }
        }
    }
}
