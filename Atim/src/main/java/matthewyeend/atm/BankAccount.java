/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.atm;

import java.util.Objects;

/**
 *
 * @author matthewyeend
 */
public class BankAccount {
    private double balance;
    
    /**
     * Constructs a bank account with a zero balance.
     */
    public BankAccount()
    {
        balance = 0;
    }
    
    /**
     * Constructs a bank account with a given balance.
     * @param initialBalance the initial balance.
     */
    public BankAccount(double initialBalance)
    {
        if (Double.isNaN(initialBalance) || Double.isInfinite(initialBalance)
            || initialBalance < 0) {
            throw new IllegalArgumentException(
                "Initial balance must be a finite, non-negative number");
        }
        balance = initialBalance;
    }
    
    /**
     * Deposits money into the bank account.
     * @param amount the amount to deposit
     */
    public void deposit(double amount)
    {
        requireValidAmount(amount);
        balance = balance + amount;
    }
    
    /**
     * Withdraws money from the bank account.
     * @param amount the amount to withdraw
     */
    public void withdraw(double amount)
    {
        requireValidAmount(amount);
        if (amount > balance) {
            throw new IllegalStateException("Insufficient funds");
        }
        balance = balance - amount;
    }
    
    /**
     * Gets the current balance of the bank account.
     * @return the current balance
     */
    public double getBalance()
    {
        return balance;
    }
    
    /**
    * Transfers money from this account to another account.
    * @param amount the amount to transfer
    * @param other the account to receive the money
    */
   public void transfer(double amount, BankAccount other)
   {
       Objects.requireNonNull(other, "Target account must not be null");
       if (other == this) {
           throw new IllegalArgumentException("Cannot transfer to the same account");
       }
       requireValidAmount(amount);
       if (amount > balance) {
           throw new IllegalStateException("Insufficient funds");
       }
       withdraw(amount);
       other.deposit(amount);
   }

   private static void requireValidAmount(double amount)
   {
       if (Double.isNaN(amount) || Double.isInfinite(amount) || amount <= 0) {
           throw new IllegalArgumentException(
                   "Amount must be a positive, finite number");
       }
   }
}
