/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package matthewyeend.selsort;

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
        
        SelectionSorter sorter = new SelectionSorter(a);
        sorter.sort();
        
        System.out.println(Arrays.toString(a));
        
        Scanner in = new Scanner(System.in);
        System.out.print("Enter array size: ");
        int n = in.nextInt();
        
        // Constructs random array
        int[] b = ArrayUtil.randomIntArray(n, 100);
        SelectionSorter sort = new SelectionSorter(b);
        
        // Uses stopwatch to time selection sort
        StopWatch timer = new StopWatch();
        timer.start();
        sort.sort();
        timer.stop();
        
        System.out.println("Elapsed time: " + timer.getElapsedTime()
            + " milliseconds");
    }
}
