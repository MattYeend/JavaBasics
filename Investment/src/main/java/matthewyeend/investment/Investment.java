/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package matthewyeend.investment;

/**
 *
 * @author matthewyeend
 */
public class Investment {
    private double balance;
    private double rate;
    private int years;
    
    /**
     * Constructs an Investment object from a starting balance and interests
     * rate.
     * @param aBalance the starting balance
     * @param aRate the interest rate in percent
     */
    public Investment(double aBalance, double aRate) {
        balance = aBalance;
        rate = aRate;
        years = 0;
    }
    
    /**
     * Keeps accumulating interest until a target balance has been reached.
     * @param targetBalance the desired balance
     */
    public void waitForBalance(double targetBalance) {
        while (balance < targetBalance) {
            years++;
            double interest = balance * rate / 100;
            balance = balance + interest;
        }
    }
    
    /**
     * Keeps accumulating interest for a given number of years.
     * @param numberOfYears the number of years to wait
     */
    public void waitYears(int numberOfYears) {
        int i = 1;
        while (i <= numberOfYears) {
            double interest = balance * rate / 100;
            balance = balance + interest;
            i++;
        }
        years = years + numberOfYears;
    }

    /**
     * Gets the current investment balance.
     * @return the current balance
     */
    public double getBalance() {
        return balance;
    }
    
    /**
     * Gets the number of ears this investment has accumulated interest.
     * @return the number of years since the start of investment
     */
    public int getYears() {
        return years;
    }
}
