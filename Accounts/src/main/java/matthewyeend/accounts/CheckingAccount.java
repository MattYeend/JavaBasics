/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.accounts;

/**
 *
 * @author matthewyeend
 */
public class CheckingAccount extends BankAccount {
    private static final int FREE_TRANSACTIONS = 3;
    private static final double TRANSACTION_FEE = 2.0;
    private int transactionCount;
    
    /**
     * Constructs a checking account with a given balance.
     * @param initialBalance the initial balance
     */
    public CheckingAccount(double initialBalance) {
        // Construct superclass
        super(initialBalance);
        
        // Initialise transaction count
        transactionCount = 0;
    }
    
    @Override
    public void deposit(double amount) {
        transactionCount++;
        // Now add amount to balance
        super.deposit(amount);
    }
    
    @Override
    public void withdraw(double amount) {
        transactionCount++;
        // Now subtract amount from balance
        super.withdraw(amount);
    }
    
    /**
     * Deducts the accumulated fees and resets the transaction count
     */
    public void deductFees() {
        if(transactionCount > FREE_TRANSACTIONS) {
            double fees = TRANSACTION_FEE * 
                    (transactionCount - FREE_TRANSACTIONS);
            super.withdraw(fees);
        }
        transactionCount = 0;
    }
}
