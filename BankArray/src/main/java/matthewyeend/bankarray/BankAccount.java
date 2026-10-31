/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.bankarray;

/**
 *
 * @author matthewyeend
 */
public class BankAccount {
    private final int accountNumber;
    private double balance;
    
    /**
     * Constructs a bank account with a given account number and balance.
     * @param accountNumber the account number
     * @param initialBalance the starting balance
     */
    public BankAccount(int accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    /**
     * Gets the account number.
     * @return the account number
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * Gets the current balance.
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }
}
