/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package matthewyeend.bankarray;

/**
 *
 * @author matthewyeend
 */
public class Main {
    public static void main(String[] args) {
        Bank firstBank = new Bank();
        firstBank.addAccount(new BankAccount(1001, 20000));
        firstBank.addAccount(new BankAccount(1015, 10000));
        firstBank.addAccount(new BankAccount(1729, 15000));
        
        double threshold = 15000;
        int count = firstBank.countBalancesAtLeast(threshold);
        System.out.println("Count: " + count);
        System.out.println("Expected: 2");
        
        int accountNumber = 1015;
        BankAccount account = firstBank.find(accountNumber);
        if(account == null)
            System.out.println("No matching accounts");
        else
            System.out.println("Balance of matching account: " + 
                    account.getBalance());
        System.out.println("Expected: 10000");
        
        BankAccount max = firstBank.getMaximum();
        System.out.println("Account with largest balance: " + 
                max.getAccountNumber());
        System.out.println("Expected: 1001");
    }
}
