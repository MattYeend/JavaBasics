/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.measure1dataset;

/**
 *
 * @author matthewyeend
 */
public class DataSet {
    private double sum;
    private Measurable maximum;
    private int count;

    /**
     * Constructs an empty data set.
     */
    public DataSet() {
        sum = 0;
        count = 0;
        maximum = null;
    }

    /**
     * Adds a measurable object to the data set.
     * @param x the object to add
     */
    public void add(Measurable x) {
        sum = sum + x.getMeasure();
        if (count == 0 || maximum.getMeasure() < x.getMeasure()) {
            maximum = x;
        }
        count++;
    }

    /**
     * Gets the average of the added measures.
     * @return the average, or 0 if no data has been added
     */
    public double getAverage() {
        if (count == 0) {
            return 0;
        }
        return sum / count;
    }

    /**
     * Gets the object with the largest measure.
     * @return the maximum, or null if no data has been added
     */
    public Measurable getMaximum() {
        return maximum;
    }
}
