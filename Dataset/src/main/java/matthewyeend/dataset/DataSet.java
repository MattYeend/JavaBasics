/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.dataset;

/**
 *
 * @author matthewyeend
 */
public class DataSet {
    private double sum;
    private double maximum;
    private int count;
    
    /**
     * Constructs an empty data set.
     */
    public DataSet() {
        sum = 0;
        count = 0;
        maximum = 0;
    }
    
    /**
     * Adds a data value to the data set.
     * @param x a data value
     */
    public void add(double x) {
        sum = sum + x;
        if(count == 0 || maximum < x) maximum = x;
        count++;
    }
    
    /**
     * Gets the average of the added data.
     * @return the average or 0 if no data has been added
     */
    public double getAverage() {
        if(count == 0) return 0;
        else return sum/count;
    }
    
    /**
     * Gets the larges of the added data.
     * @return the maximum or 0 if no data has been added
     */
    public double getMaximum() {
        return maximum;
    }
}
