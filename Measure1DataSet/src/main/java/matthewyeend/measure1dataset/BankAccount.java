/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.measure1dataset;

/**
 *
 * @author matthewyeend
 */
public class BankAccount implements Measurable {
    private double balance;

    /**
     * Constructs a bank account with a given balance.
     * @param initialBalance the starting balance
     */
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    /**
     * Gets the current balance.
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    @Override
    public double getMeasure() {
        return balance;
    }
}
