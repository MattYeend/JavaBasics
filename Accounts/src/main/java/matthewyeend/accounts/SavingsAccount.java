/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.accounts;

/**
 *
 * @author matthewyeend
 */
public class SavingsAccount extends BankAccount {
    private double interestRate;
    
    /**
     * Constructs a bank account with a given interest rate.
     * @param rate the interest rate
     */
    public SavingsAccount(double rate) {
        if (Double.isNaN(rate) || Double.isInfinite(rate) || rate < 0) {
            throw new IllegalArgumentException(
                    "Interest rate must be a finite, non-negative number");
        }
        interestRate = rate;
    }
    
    /**
     * Adds the earned interest to the account balance.
     */
    public void addInterest() {
        double interest = getBalance() * interestRate / 100;
        if (interest > 0) {
            deposit(interest);
        }
    }
}
