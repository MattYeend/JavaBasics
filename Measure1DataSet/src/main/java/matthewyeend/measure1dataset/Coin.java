/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.measure1dataset;

/**
 *
 * @author matthewyeend
 */
public class Coin implements Measurable {
    private final double value;
    private final String name;

    /**
     * Constructs a coin.
     * @param value the monetary value of the coin
     * @param name the name of the coin
     */
    public Coin(double value, String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * Gets the coin value.
     * @return the value
     */
    public double getValue() {
        return value;
    }

    /**
     * Gets the coin name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    @Override
    public double getMeasure() {
        return value;
    }
}
