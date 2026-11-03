/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.atm;

/**
 *
 * @author matthewyeend
 */
public class Customer {
    private int customerNumber;
    private int pin;
    private BankAccount checkingAccount;
    private BankAccount savingsAccount;
    
    /**
     * Constructs a customer with a given number and PIN.
     * @param aNumber the customer number
     * @param aPin the PIN
     */
    public Customer(int aNumber, int aPin) {
        customerNumber = aNumber;
        pin = aPin;
        checkingAccount = new BankAccount();
        savingsAccount = new BankAccount();
    }
    
    /**
     * Tests if this customer matches a customer number and PIN.
     * @param aNumber a customer number
     * @param aPin a PIN
     * @return true if the customer number and PIN match
     */
    public boolean match(int aNumber, int aPin) {
        return customerNumber == aNumber && pin == aPin;
    }
    
    /** 
     * Gets the checking account of this customer.
     * @return the checking account
     */
    public BankAccount getCheckingAccount() {
        return checkingAccount;
    }
    
    /**
     * Gets the savings account of this customer.
     * @return the savings account
     */
    public BankAccount getSavingsAccount() {
        return savingsAccount;
    }
}
