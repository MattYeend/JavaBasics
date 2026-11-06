/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.linsearch;

/**
 *
 * @author matthewyeend
 */
public class LinearSearch {
    private int[] a;
    
    /**
     * Constructs the LinearSearch.
     * @param anArray an array of integers
     */
    public LinearSearch(int[] anArray) {
        a = anArray;
    }
    
    /**
     * Finds a value in an array, using the linear search algorithm.
     * @param v the value of the search
     * @return the index at which the value occurs, or -1 if it does not occur
     * in the array
     */
    public int search(int v) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == v) 
                return i;
        }
        return -1;
    }
}
