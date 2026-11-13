/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.sync;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author matthewyeend
 */
public class BankAccount {
    private double balance;
    private final Lock balanceChangeLock;
    private final Condition sufficientFundsCondition;
    
    /**
     * Constructs a bank account with a zero balance.
     */
    public BankAccount() {
        balance = 0;
        balanceChangeLock = new ReentrantLock();
        sufficientFundsCondition = balanceChangeLock.newCondition();
    }
    
    /**
     * Deposits money into the bank account.
     * @param amount the amount to deposit
     */
    public void deposit(double amount) {
        balanceChangeLock.lock();
        try {
            System.out.print("Depositing " + amount);
            double newBalance = balance + amount;
            System.out.println(", new balance is " + newBalance);
            balance = newBalance;
            sufficientFundsCondition.signalAll();
        } finally {
            balanceChangeLock.unlock();
        }
    }
    
    /**
     * Withdraws money from the bank account.
     * @param amount the amount to withdraw
     * @throws java.lang.InterruptedException
     */
    public void withdraw(double amount) throws InterruptedException {
        balanceChangeLock.lock();
        try {
            while (balance < amount)
                sufficientFundsCondition.await();
            System.out.print("Withdrawing " + amount);
            double newBalance = balance - amount;
            System.out.println(", new balance is" + newBalance);
            balance = newBalance;
        } finally {
            balanceChangeLock.unlock();
        }
    }
    
    /**
     * Gets the current balance of the bank account.
     * @return the current balance
     */
    public double getBalance() {
        return balance;
    }
}
