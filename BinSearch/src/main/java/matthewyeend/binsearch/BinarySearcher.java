/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.binsearch;

import java.util.Objects;

/**
 *
 * @author matthewyeend
 */
public class BinarySearcher {

    private final int[] a;

    /**
     * Constructs a BinarySearcher.
     *
     * @param anArray a sorted array of integers, which must not be null
     * @throws NullPointerException if anArray is null
     */
    public BinarySearcher(int[] anArray) {
        a = Objects.requireNonNull(anArray, "anArray must not be null").clone();
    }

    /**
     * Finds a value in a sorted array, using the binary search algorithm.
     *
     * @param v the value to search for
     * @return the index at which the value occurs, or -1 if it does not
     * occur in the array
     */
    public int search(int v) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int diff = Integer.compare(a[mid], v);
            if (diff == 0) { // a[mid] == v
                return mid;
            } else if (diff < 0) { // a[mid] < v
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
