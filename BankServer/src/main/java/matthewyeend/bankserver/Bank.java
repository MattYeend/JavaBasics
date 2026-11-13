/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.bankserver;

import java.math.BigDecimal;

/**
 *
 * @author matthewyeend
 */
public class Bank {    
    private final BankAccount[] accounts;

    /**
     * Constructs a bank with a given number of accounts.
     * @param size the number of accounts, which must be positive
     */
    public Bank(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Invalid bank size");
        }
        accounts = new BankAccount[size];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new BankAccount();
        }
    }
    
    /**
     * Deposits money into a bank account.
     * @param accountNumber the account number
     * @param amount the amount to deposit
     */
    public void deposit(int accountNumber, BigDecimal amount) {
        findAccount(accountNumber).deposit(amount);
    }
    
    /**
     * Withdraws money into a bank account.
     * @param accountNumber the account number
     * @param amount the amount to withdraw
     */
    public void withdraw(int accountNumber, BigDecimal amount) {
        findAccount(accountNumber).withdraw(amount);
    }
    
    /**
     * Gets the balance of a bank account.
     * @param accountNumber the account number
     * @return the account balance
     */
    public BigDecimal getBalance(int accountNumber) {
        return findAccount(accountNumber).getBalance();
    }
    
    /**
     * Looks up an account by its number, validating the number first.
     * 
     * The account number is treated as entrusted input, so it is checked
     * against the bounds of the account array before use. The exception
     * message is deliberately generic to avoid disclosing internal detail
     * to callers.
     *
     * @param accountNumber the zero-based account number
     * @return the matching {@link BankAccount}, never {@code null}
     * @throws IllegalArgumentException if {@code accountNumber} is negative
     *         or greater than or equal to the number of accounts held by the bank
     */
    private BankAccount findAccount(int accountNumber) {
        if (accountNumber < 0 || accountNumber >= accounts.length) {
            throw new IllegalArgumentException("Invalid account number");
        }
        return accounts[accountNumber];
    }
}
