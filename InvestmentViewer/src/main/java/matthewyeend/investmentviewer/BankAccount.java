/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.investmentviewer;

/**
 *
 * @author matthewyeend
 */
public class BankAccount {
    /**
     * The current balance of the account.
     */
    private double balance;

    /**
     * Constructs a bank account with the given starting balance.
     *
     * @param initialBalance the balance the account starts with
     */
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    /**
     * Deposits money into the account, increasing the balance.
     *
     * @param amount the amount to deposit
     */
    public void deposit(double amount) {
        balance += amount;
    }

    /**
     * Withdraws money from the account, decreasing the balance.
     *
     * @param amount the amount to withdraw
     */
    public void withdraw(double amount) {
        balance -= amount;
    }

    /**
     * Gets the current balance of the account.
     *
     * @return the current balance
     */
    public double getBalance() {
        return balance;
    }
}
